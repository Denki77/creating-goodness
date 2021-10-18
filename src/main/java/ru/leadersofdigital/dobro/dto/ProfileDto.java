package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private String firstname;
    private String lastname;
    private String comment;
    private String mail;
    private Long userId;
}
