package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.repositories.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getMeAllRoles() {
        return roleRepository.getRolesByStatusGreaterThanEqual(1);
    }
}
