package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.repositories.ShelterRepository;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final ShelterRepository repository;

    /*public List<AttributeObject> findAll (String name) {
        return repository.findAll(AttributeSpecification.likeBuild(name), PageRequest.of(0, 10)).toList();
    }*/



}
