package ru.leadersofdigital.dobro.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
