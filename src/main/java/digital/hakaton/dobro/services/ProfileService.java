package digital.hakaton.dobro.services;

import digital.hakaton.dobro.dto.ProfileDto;
import digital.hakaton.dobro.errorHandling.ResourceNotFoundException;
import digital.hakaton.dobro.models.*;
import digital.hakaton.dobro.repositories.ProfileRepository;
import digital.hakaton.dobro.repositories.RoleRepository;
import digital.hakaton.dobro.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserService userService;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final static Function<Profile, ProfileDto> functionToDto = profile -> {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profile.getId());
        profileDto.setFirstname(profile.getFirstname());
        profileDto.setLastname(profile.getLastname());
        profileDto.setUsername(profile.getUser().getUsername());
        profileDto.setAnnotation(profile.getAnnotation());
        profileDto.setDescription(profile.getDescription());
        profileDto.setEmail(profile.getUser().getEmail());
        profileDto.setInterests(profile.getInterests().stream().map(Interest::getInterest).collect(Collectors.toList()));
        profileDto.setTags(profile.getTags().stream().map(Tag::getTags).collect(Collectors.toList()));
        return profileDto;
    };


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
        List<String> roles = roleRepository.getUserRolesById(id).stream().map(Role::getCode).collect(Collectors.toList());
        return roles.contains("ROLE_ADMIN");
    }

    public void update(ProfileDto profileDto) {
        Profile profile = profileRepository.getProfileById(profileDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Profile doesn't exists id: " + profileDto.getId()));
        profile.setFirstname(profileDto.getFirstname());
        profile.setLastname(profileDto.getLastname());
        profile.setAnnotation(profileDto.getAnnotation());
        profile.setDescription(profileDto.getDescription());
    }

    public ProfileDto getProfileDtoByUserName(String email) {
        User user = userRepository.getUserByEmail(email);
        Profile profile = profileRepository.getProfileByUser(user);
        return getProfileDtoByProfile(profile);
    }

    public List<ProfileDto> getListOfProfileDtoByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User doesn't exists id: " + userId));
        return user.getProfiles().stream().map(functionToDto).collect(Collectors.toList());
    }
}