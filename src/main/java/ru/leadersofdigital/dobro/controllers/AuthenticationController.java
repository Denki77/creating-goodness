package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dtos.LoginDto;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public String register(@RequestBody @Validated RegisterDto dto) {
        return service.registerUser(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDto dto) {
        return service.authorization(dto);
    }

    @GetMapping("/logout")
    public void logout() {
        // разлогинить ннадо?! или достаточно на фронте грохнуть токен
    }
}