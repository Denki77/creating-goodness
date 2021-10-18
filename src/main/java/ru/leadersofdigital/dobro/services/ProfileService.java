package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.ProfileRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    private final Function<Profile, ProfileDto> functionToDto = profile -> {
        ProfileDto dto = new ProfileDto();
        dto.setFirstname(profile.getFirstname());
        dto.setLastname(profile.getLastname());
        dto.setComment(profile.getComment());
        dto.setMail(profile.getUser().getEmail());
        dto.setUserId(profile.getUser().getId());
        return dto;
    };

    public ProfileDto getByUsername(String name){
        Profile profile = profileRepository.getByEmail(name);
        return functionToDto.apply(profile);
    }

    public ProfileDto getByUserId(Long id){
        Profile profile = profileRepository.getByUserId(id);
        return functionToDto.apply(profile);
    }

    public void update(ProfileDto dto){
        Profile profile = profileRepository.getByUserId(dto.getUserId());
        profile.setFirstname(dto.getFirstname());
        profile.setLastname(dto.getLastname());
        profile.setComment(dto.getComment());
        profileRepository.saveAndFlush(profile);
    }
}
