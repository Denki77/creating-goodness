package digital.hakaton.dobro.controllers;

import digital.hakaton.dobro.dto.ShelterDto;
import digital.hakaton.dobro.models.City;
import digital.hakaton.dobro.models.Role;
import digital.hakaton.dobro.services.CityService;
import digital.hakaton.dobro.services.RoleService;
import digital.hakaton.dobro.services.ShelterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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