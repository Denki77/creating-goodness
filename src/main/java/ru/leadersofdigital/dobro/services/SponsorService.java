package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.RoleRepository;
import ru.leadersofdigital.dobro.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class SponsorService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

//    public Page<User> findPage(int page, int pageSize) {
//        Role role = roleRepository.findByName("sponsor").orElseThrow(() -> new ResourceNotFoundException("Role 'Sponsor' doesn't exists"));
//        return userRepository.findByRole(role, PageRequest.of(page, pageSize));
//    }
}
