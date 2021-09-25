package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.leadersofdigital.dobro.models.User;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Long shelter_id;
    private String role_code;

    public UserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        //this.shelter_id = user.getShelter().getId();
        //this.shelter_id = user.getShelter_id();
        //this.role_code = user.getRole().getCode();
    }
}
