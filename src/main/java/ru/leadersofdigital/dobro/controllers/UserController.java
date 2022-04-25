package ru.leadersofdigital.dobro.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dto.UserDto;
import ru.leadersofdigital.dobro.services.UserService;

@Tag(name = "Users", description = "The user API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;

    @Operation(summary = "Create new user", tags = "Users")
    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
    }
}
