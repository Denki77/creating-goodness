package ru.leadersofdigital.dobro.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.repositories.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;


    public Role findById (Long id) {
        return roleRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("NotFound role id " + id));
    }



}
