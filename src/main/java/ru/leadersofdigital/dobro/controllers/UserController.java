package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dto.UserDto;
import ru.leadersofdigital.dobro.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {

        userService.createNewUser(userDto);
    }
}
