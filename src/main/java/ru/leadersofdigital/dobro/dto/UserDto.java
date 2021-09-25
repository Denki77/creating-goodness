package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import ru.leadersofdigital.dobro.models.User;

@Data
public class UserDto {
    private String username;
    private String email;
    private String annotation;
    private String about;
    private String mainPhoto;

    public UserDto(User user) {
        this.username = username;
        this.email = email;
    }
}
