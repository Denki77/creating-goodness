package ru.leadersofdigital.dobro.controllers.interfaces;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dtos.LoginDto;
import ru.leadersofdigital.dobro.dtos.RegisterDto;
import ru.leadersofdigital.dobro.dtos.TokenDto;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthenticationController {


    @PostMapping("/register")
    String register (@Validated @RequestBody RegisterDto dto);

    @PostMapping("/login")
    String login (@Validated @RequestBody LoginDto dto);

}
