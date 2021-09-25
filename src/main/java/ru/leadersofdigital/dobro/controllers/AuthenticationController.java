package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/")
    public void auth () {
        System.out.println("");
    }


    @PostMapping("/register")
    public String register (@Validated @RequestBody RegisterDto dto) {
        return service.registerUser(dto);
    }













}
