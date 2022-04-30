package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String annotation;
    private String description;
    private String email;
    private String role;
    private Long userId;
    private List<String> interests;
    private List<String> tags;
}