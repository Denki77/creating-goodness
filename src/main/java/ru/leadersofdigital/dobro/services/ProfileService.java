package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.enums.Permissions;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.repositories.ProfileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    public void getByUser(String name){

    }

    public ProfileDto getByUserId(Long id) {
        Profile profile = profileRepository.getByUserId(id);
        ProfileDto dto = new ProfileDto();
        dto.setFirstname(profile.getFirstname());
        dto.setLastname(profile.getLastname());
        dto.setComment(profile.getComment());
        dto.setMail(profile.getUser().getEmail());
        dto.setUserId(profile.getUser().getId());
        return dto;
    }

    public boolean isAdminRole(Long id) {
        List<String> roles = profileRepository.getRolesByUserId(id);
        return roles.contains(Permissions.ROLE_ABSOLUTE.getCode());
    }

    public void update(ProfileDto dto) {
        Profile profile = profileRepository.getByUserId(dto.getUserId());
        profile.setFirstname(dto.getFirstname());
        profile.setLastname(dto.getLastname());
        profile.setComment(dto.getComment());
    }
}