package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.services.RoleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final RoleService roleService;

    @PostMapping("/")
    public void auth() {
        System.out.println("");
    }


    @PostMapping("/register")
    public void register() {
        System.out.println("");
    }

    @GetMapping("/get_me_roles")
    public List<Role> get_me_roles() {
        return roleService.getMeAllRoles();
    }













}
