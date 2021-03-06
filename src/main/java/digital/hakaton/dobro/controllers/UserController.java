package digital.hakaton.dobro.controllers;

import digital.hakaton.dobro.dto.UserDto;
import digital.hakaton.dobro.models.User;
import digital.hakaton.dobro.services.AuthenticationFacade;
import digital.hakaton.dobro.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "Users", description = "The user API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @Operation(summary = "Get authorize user", tags = "Users")
    @GetMapping
    public UserDto getUser() {
        return userService.getUserById(authenticationFacade.getUserId());
    }

    @Operation(summary = "Get all users by admin", tags = "Users")
    @GetMapping("/admin/")
    public Page<UserDto> getAllUsers(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "count", defaultValue = "10") int count
    ) {
        Page<User> usersPage = userService.findPage(page - 1, count);
        return new PageImpl<>(usersPage.getContent().stream().map(UserDto::new).collect(Collectors.toList()), usersPage.getPageable(), usersPage.getTotalElements());
    }

    @Operation(summary = "Create new user by admin", tags = "Users")
    @PostMapping("/admin/register")
    public void register(@RequestBody UserDto userDto) {
        userService.createNewUser(userDto);
    }

    @Operation(summary = "Get one user by admin", tags = "Users")
    @GetMapping("/admin/{id}")
    public UserDto getOneUser(
            @PathVariable Long id
    ) {
        return userService.getUserById(id);
    }
}