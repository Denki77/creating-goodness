package ru.leadersofdigital.dobro.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.services.AuthenticationFacade;
import ru.leadersofdigital.dobro.services.ProfileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Tag(name = "Profile", description = "The profile API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final AuthenticationFacade authenticationFacade;
    private final ProfileService profileService;

    @GetMapping()
    @Operation(summary = "Get profile authenticate user", tags = "Profile")
    public ProfileDto getUserProfile() {
        return profileService.getProfileDtoByUserName(authenticationFacade.getAuthentication().getName());
    }

    @Operation(summary = "Get profiles by user id", tags = "Profile")
    @GetMapping("/user/{id}")
    public List<ProfileDto> getProfileByUserId(@PathVariable Long id, HttpServletResponse httpResponse) throws IOException {
        List<ProfileDto> profileServiceByUserIdUser = profileService.getListOfProfileDtoByUserId(id);
        if (!profileServiceByUserIdUser.isEmpty()) {
            return profileServiceByUserIdUser;
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