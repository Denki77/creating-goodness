package ru.leadersofdigital.dobro.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.error_handling.InvalidDataException;
import ru.leadersofdigital.dobro.models.City;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.Shelter;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.UserRepository;
import ru.leadersofdigital.dobro.utils.JwtTokenUtil;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
    private final JwtTokenUtil tokenUtil;
    private final UserRepository userRepository;
    private final RoleService roleService;


    public String registerUser (RegisterDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new InvalidDataException(List.of("email"));
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(cryptPasswordEncoder.encode(dto.getPassword()));
        user.setRoles(List.of(new Role(dto.getRole())));

        if (dto.getShelter() != null) {
            user.setShelter(new Shelter(dto.getShelter()));
        }
        user = userRepository.save(user);
        return tokenUtil.generateToken(user);
    }







}
