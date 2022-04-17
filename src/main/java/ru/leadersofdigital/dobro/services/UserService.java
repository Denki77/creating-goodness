package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.UserDto;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.User;
import ru.leadersofdigital.dobro.repositories.ProfileRepository;
import ru.leadersofdigital.dobro.repositories.RoleRepository;
import ru.leadersofdigital.dobro.repositories.UserRepository;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void setProfileRepository(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getEmail()));
        userRepository.saveAndFlush(user);

        Profile profile = new Profile();
        profile.setUser(user);
        profileRepository.saveAndFlush(profile);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCode().toUpperCase(Locale.ROOT))).collect(Collectors.toList());
    }
}