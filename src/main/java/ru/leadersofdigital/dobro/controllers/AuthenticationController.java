package ru.leadersofdigital.dobro.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dtos.LoginDto;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.services.AuthenticationService;

@Tag(name = "Auth", description = "The auth API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @Operation(summary = "Register user", tags = "auth")
    @PostMapping("/register")
    public String register(@RequestBody @Validated RegisterDto dto) {
        return service.registerUser(dto);
    }

    @Operation(summary = "Login user", tags = "auth")
    @PostMapping("/login")
    public String login(@RequestBody @Validated LoginDto dto) {
        return service.authorization(dto);
    }

    @Deprecated
    @Operation(summary = "Logout user", tags = "auth")
    @GetMapping("/logout")
    public void logout() {
        // разлогинить ннадо?! или достаточно на фронте грохнуть токен
    }
}