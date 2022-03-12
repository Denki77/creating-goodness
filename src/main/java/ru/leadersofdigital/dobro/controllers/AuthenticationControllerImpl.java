package ru.leadersofdigital.dobro.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.controllers.interfaces.AuthenticationController;
import ru.leadersofdigital.dobro.dtos.LoginDto;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.dtos.TokenDto;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.services.AuthenticationService;


@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService service;

    @Override
    public String register(RegisterDto dto) {
        return service.registerUser(dto);
    }

    @Override
    public String login(LoginDto dto) {
        return service.authorization(dto);
    }


}
