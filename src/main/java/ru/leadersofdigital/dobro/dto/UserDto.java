package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.leadersofdigital.dobro.models.User;

import java.util.Collection;

@Data
@NoArgsConstructor
public class UserDto implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
