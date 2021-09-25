package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.UserDto;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.RoleRepository;
import ru.leadersofdigital.dobro.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void createNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getEmail()));
        //user.setRole(roleRepository.getRoleByCode(userDto.getRole_code()));
        //user.setShelter_id(user.getShelter_id());
        userRepository.saveAndFlush(user);
    }
}
