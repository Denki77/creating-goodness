package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.AttributeObject;
import ru.leadersofdigital.dobro.models.Shelter;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long>, JpaSpecificationExecutor<AttributeObject> {

    List<Shelter> findByNameStartsWith(String q);
}
