package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private String firstname;
    private String lastname;
    private String annotation;
    private String description;
    private String email;
    private Long userId;
}