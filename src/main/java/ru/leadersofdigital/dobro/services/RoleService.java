package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.enums.ModerateStatus;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.repositories.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getMeAllRoles() {
        return roleRepository.getRolesByStatusGreaterThanEqual(ModerateStatus.APPROVE.ordinal());
    }


    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NotFound role id " + id));
    }

    public Role getMeRoleByCode(String code) {
        return roleRepository.getRoleByCode(code);
    }
}