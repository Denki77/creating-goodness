package ru.leadersofdigital.dobro.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotNull(message = "Field That's incredible! empty")
    private Long role;

    private Long shelter;

    @NotNull(message = "Field That's incredible! empty")
    private String username;

    @NotNull(message = "Field That's incredible! empty")
    @Email(message = "Not correct email address")
    private String email;

    @NotNull(message = "Field That's incredible! empty")
    private Long city;

    @NotNull(message = "Field That's incredible! empty")
    @Length(min = 6)
    private String password;

}
