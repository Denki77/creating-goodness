package ru.leadersofdigital.dobro.controllers.interfaces;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.leadersofdigital.dobro.dtos.AttributeDto;
import ru.leadersofdigital.dobro.models.Role;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
public interface AttributeController {

    @GetMapping("/city")
    List<AttributeDto> findCity(@RequestParam(name = "q", defaultValue = "") String q);

    @GetMapping("/shelter")
    List<AttributeDto> findShelter(@RequestParam(name = "q", defaultValue = "") String q);

    @GetMapping("/get_me_roles")
    List<Role> get_me_roles();


}
