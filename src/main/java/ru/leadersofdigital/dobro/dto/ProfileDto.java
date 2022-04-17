package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private String firstname;
    private String lastname;
    private String username;
    private String annotation;
    private String description;
    private String email;
    private String role;
    private Long userId;
}