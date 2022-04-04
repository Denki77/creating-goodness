package ru.leadersofdigital.dobro.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dtos.LoginDto;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.error_handling.InvalidDataException;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.Shelter;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.ProfileRepository;
import ru.leadersofdigital.dobro.repositories.UserRepository;
import ru.leadersofdigital.dobro.utils.JwtTokenUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
    private final JwtTokenUtil tokenUtil;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    private ProfileRepository profileRepository;


    public String authorization (LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("Email not found"));

        if (!cryptPasswordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidDataException(List.of("Invalid data password or email"));
        }

        return tokenUtil.generateToken(user);
    }

    public String registerUser (RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new InvalidDataException(List.of("email"));
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));

        if (dto.getShelter() != null) {
            user.setShelter(new Shelter(dto.getShelter()));
        }
        if (dto.getRole() != null) {
            user.setRoles(List.of(new Role(dto.getRole())));
        }
        user = userRepository.save(user);
        Profile profile = new Profile();
        profile.setUser(user);
        profileRepository.saveAndFlush(profile);
        return tokenUtil.generateToken(user);
    }
}
