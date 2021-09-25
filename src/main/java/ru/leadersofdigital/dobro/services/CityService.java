package ru.leadersofdigital.dobro.services;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.models.AttributeObject;
import ru.leadersofdigital.dobro.models.City;
import ru.leadersofdigital.dobro.repositories.CityRepository;
import ru.leadersofdigital.dobro.repositories.specification.AttributeSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    public List<AttributeObject> findAll (String name) {
        return repository.findAll(AttributeSpecification.likeBuild(name),PageRequest.of(0, 10)).toList();
    }







}
