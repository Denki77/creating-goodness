package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.models.City;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.Shelter;
import ru.leadersofdigital.dobro.services.CityService;
import ru.leadersofdigital.dobro.services.RoleService;
import ru.leadersofdigital.dobro.services.ShelterService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attribute")
public class AttributeController {
    private final CityService cityService;
    private final RoleService roleService;
    private final ShelterService shelterService;

    @GetMapping("/city")
    public List<City> findCity(String q) {
        if (q == null) {
            return cityService.getMeAllCities();
        }
        return cityService.getMeAllCities(q);
    }

    @GetMapping("/shelter")
    public List<Shelter> findShelter(String q) {
        if (q == null) {
            return shelterService.getMeAllShelters();
        }
        return shelterService.getMeAllShelters(q);
    }

    @GetMapping("/role")
    public List<Role> get_me_roles() {
        return roleService.getMeAllRoles();
    }
}