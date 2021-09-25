package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dtos.AttributeDto;
import ru.leadersofdigital.dobro.policy.AttributePolicy;
import ru.leadersofdigital.dobro.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
@RequiredArgsConstructor
public class AttributeController {
    private final AttributePolicy attributePolicy;
    private final RoleService roleService;

    @GetMapping("/city")
    public List<AttributeDto> findCity(@RequestParam(name = "q", defaultValue = "") String q) {
        return attributePolicy.getCities(q);
    }

    /*@GetMapping("/shelter")
    public List<AttributeDto> findShelter(@RequestParam(name = "q", defaultValue = "") String q) {
        return attributePolicy.getShelters(q);
    }*/

//    @GetMapping("/get_me_roles")
//    public List<Role> get_me_roles() {
//        return roleService.getMeAllRoles();
//    }

}
