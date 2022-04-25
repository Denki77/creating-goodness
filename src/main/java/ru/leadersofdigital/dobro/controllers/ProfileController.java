package ru.leadersofdigital.dobro.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.services.AuthenticationFacade;
import ru.leadersofdigital.dobro.services.ProfileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Tag(name = "Profile", description = "The profile API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private AuthenticationFacade facade;

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    @Operation(summary = "Get profile authenticate user", tags = "Profile")
    public ProfileDto getUserProfile() {
        return profileService.getProfileDtoByUserName(facade.getAuthentication().getName());
    }

    @Operation(summary = "Get profile by user id", tags = "Profile")
    @GetMapping("/user/{id}")
    public ProfileDto getProfileByUserId(@PathVariable Long id, HttpServletResponse httpResponse) throws IOException {
        ProfileDto profileUser = profileService.getByUserId(id);
        if (
                facade.getAuthentication().isAuthenticated()
                        && facade.getAuthentication().getName().equals(profileUser.getEmail())
        ) {
            return profileUser;
        }
        httpResponse.sendError(403);
        httpResponse.sendRedirect("/");
        return null;
    }

    @Operation(summary = "Update profile", tags = "Profile")
    @PostMapping
    public void update(@RequestBody ProfileDto profileDto) {
        profileService.update(profileDto);
    }
}