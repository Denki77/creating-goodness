package digital.hakaton.dobro.services;

import digital.hakaton.dobro.dto.UserDto;
import digital.hakaton.dobro.models.Profile;
import digital.hakaton.dobro.models.Role;
import digital.hakaton.dobro.models.User;
import digital.hakaton.dobro.repositories.ProfileRepository;
import digital.hakaton.dobro.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

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

    public List<Profile> getAllUserProfiles(Long userId) {
        return profileRepository.getProfilesByUserId(userId);
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

    public Page<User> findPage(int page, int pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }

    public UserDto getUserById(Long userId) {
        return new UserDto(userRepository.getById(userId));
    }

    public List<String> getUserRolesByUserId(Long userId) {
        return this.getUserById(userId).getRoles();
    }
}