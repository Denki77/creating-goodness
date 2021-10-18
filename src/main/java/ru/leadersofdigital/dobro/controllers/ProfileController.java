package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.services.ProfileService;
import ru.leadersofdigital.dobro.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping()
    public ProfileDto getUserProfile(@RequestHeader("Authorization") String authHeader){
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            String username = jwtTokenUtil.getName(jwt);
            return profileService.getByUsername(username);
        }
        return null;
    }

    @GetMapping("/user/{id}")
    public ProfileDto getProfileByUserId(@PathVariable Long id){
        return profileService.getByUserId(id);
    }

    @PostMapping
    public void update(@RequestBody ProfileDto profileDto){
        profileService.update(profileDto);
    }
}
