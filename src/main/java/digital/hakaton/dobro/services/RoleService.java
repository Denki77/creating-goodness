package digital.hakaton.dobro.services;

import digital.hakaton.dobro.enums.ModerateStatus;
import digital.hakaton.dobro.errorHandling.ResourceNotFoundException;
import digital.hakaton.dobro.models.Role;
import digital.hakaton.dobro.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getMeAllRoles() {
        return roleRepository.getRolesByStatusIs(ModerateStatus.APPROVE.ordinal());
    }

    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NotFound role id " + id));
    }

    public Role getMeRoleByCode(String code) {
        return roleRepository.getRoleByCode(code);
    }
}