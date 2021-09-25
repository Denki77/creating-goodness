package ru.leadersofdigital.dobro.policy;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dtos.AttributeDto;
import ru.leadersofdigital.dobro.services.CityService;
import ru.leadersofdigital.dobro.services.ShelterService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttributePolicy {
    private final CityService cityService;
    private final ShelterService shelterService;


    public List<AttributeDto> getCities (String q) {
        return cityService.findAll(q).stream().map(AttributeDto::new).collect(Collectors.toList());
    }

    /*public List<AttributeDto> getShelters (String q) {
        return shelterService.findAll(q).stream().map(AttributeDto::new).collect(Collectors.toList());
    }*/





}
