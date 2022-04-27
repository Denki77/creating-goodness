package ru.leadersofdigital.dobro.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dto.ShelterDto;
import ru.leadersofdigital.dobro.models.City;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.services.CityService;
import ru.leadersofdigital.dobro.services.RoleService;
import ru.leadersofdigital.dobro.services.ShelterService;

import java.util.List;

@Tag(name = "Attribute", description = "The attributes API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
public class AttributeController {
    private final CityService cityService;
    private final RoleService roleService;
    private final ShelterService shelterService;

    @GetMapping("/city")
    public List<City> findCity(String search) {
        if (search == null) {
            return cityService.getMeAllCities();
        }
        return cityService.getMeAllCities(search);
    }

    @GetMapping("/shelter")
    public List<ShelterDto> findShelter(String search) {
        if (search == null) {
            return shelterService.getMeAllSheltersAsShelterDtoS();
        }
        return shelterService.getMeAllSheltersAsShelterDtoS(search);
    }

    @GetMapping("/role")
    public List<Role> get_me_roles() {
        return roleService.getMeAllRoles();
    }
}