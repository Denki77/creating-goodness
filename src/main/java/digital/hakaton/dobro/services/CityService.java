package digital.hakaton.dobro.services;


import digital.hakaton.dobro.models.AttributeObject;
import digital.hakaton.dobro.models.City;
import digital.hakaton.dobro.repositories.CityRepository;
import digital.hakaton.dobro.repositories.specification.AttributeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    public List<AttributeObject> findAll(String name) {
        return repository.findAll(AttributeSpecification.likeBuild(name), PageRequest.of(0, 10)).toList();
    }

    public List<City> getMeAllCities() {
        return repository.findAll();
    }

    public List<City> getMeAllCities(String q) {
        return repository.findByNameStartsWith(q);
    }
}