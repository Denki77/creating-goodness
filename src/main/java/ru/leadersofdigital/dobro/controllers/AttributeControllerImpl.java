package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.controllers.interfaces.AttributeController;
import ru.leadersofdigital.dobro.dtos.AttributeDto;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.policy.AttributePolicy;
import ru.leadersofdigital.dobro.services.RoleService;

import java.util.List;

@RequiredArgsConstructor
public class AttributeControllerImpl implements AttributeController {
    private final AttributePolicy attributePolicy;
    private final RoleService roleService;

    @Override
    public List<AttributeDto> findCity(String q) {
        return attributePolicy.getCities(q);
    }

    @Override
    public List<AttributeDto> findShelter(String q) {
        return attributePolicy.getShelters(q);
    }

    @Override
    public List<Role> get_me_roles() {
        return roleService.getMeAllRoles();
    }

}
