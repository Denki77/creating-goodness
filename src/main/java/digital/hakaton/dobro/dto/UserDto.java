package digital.hakaton.dobro.dto;

import digital.hakaton.dobro.models.Role;
import digital.hakaton.dobro.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<String> roles;
    private LocalDate updateAt;
    private LocalDate createAt;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(Role::getCode).collect(Collectors.toList());
        this.updateAt = user.getUpdatedAt();
        this.createAt = user.getCreatedAt();
    }
}