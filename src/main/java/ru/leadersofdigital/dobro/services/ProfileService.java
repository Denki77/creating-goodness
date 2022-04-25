package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.enums.Permissions;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.ProfileRepository;
import ru.leadersofdigital.dobro.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileDto getByUserId(Long id) {
        Profile profile = profileRepository.getByUserId(id);
        return getProfileDtoByProfile(profile);
    }

    private ProfileDto getProfileDtoByProfile(Profile profile) {
        ProfileDto dto = new ProfileDto();
        if (profile == null) {
            return dto;
        }
        dto.setFirstname(profile.getFirstname());
        dto.setUsername(profile.getUser().getUsername());
        dto.setLastname(profile.getLastname());
        dto.setAnnotation(profile.getAnnotation());
        dto.setDescription(profile.getDescription());
        dto.setEmail(profile.getUser().getEmail());
        dto.setUserId(profile.getUser().getId());
        dto.setRole(profile.getUser().getRoles().get(0).getName());
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
        profile.setAnnotation(dto.getAnnotation());
        profile.setDescription(dto.getDescription());
    }

    public ProfileDto getProfileDtoByUserName(String email) {
        User user = userRepository.getUserByEmail(email);
        Profile profile = profileRepository.getProfileByUser(user);
        return getProfileDtoByProfile(profile);
    }

    public ProfileDto getProfileDtoByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User doesn't exists id: " + userId));
        Profile profile = profileRepository.getProfileByUser(user);
        return getProfileDtoByProfile(profile);
    }
}