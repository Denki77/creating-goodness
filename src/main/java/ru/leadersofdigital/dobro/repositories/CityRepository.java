package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.leadersofdigital.dobro.models.AttributeObject;
import ru.leadersofdigital.dobro.models.City;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> , JpaSpecificationExecutor<AttributeObject> {

    List<City> findByNameStartsWith(String q);
}
