package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {


}
