package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.services.AuthenticationFacade;
import ru.leadersofdigital.dobro.services.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private AuthenticationFacade facade;

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public void getUserProfile(){
        System.out.println(facade.getAuthentication().getName());
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