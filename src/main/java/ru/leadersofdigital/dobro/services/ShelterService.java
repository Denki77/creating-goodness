package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.models.AttributeObject;
import ru.leadersofdigital.dobro.models.Shelter;
import ru.leadersofdigital.dobro.repositories.ShelterRepository;
import ru.leadersofdigital.dobro.repositories.specification.AttributeSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShelterService {
    private final ShelterRepository repository;

    public List<AttributeObject> findAll(String name) {
        return repository.findAll(AttributeSpecification.likeBuild(name), PageRequest.of(0, 10)).toList();
    }

    public List<Shelter> getMeAllShelters() {
        return repository.findAll();
    }

    public List<Shelter> getMeAllShelters(String q) {
        return repository.findByNameStartsWith(q);
    }
}