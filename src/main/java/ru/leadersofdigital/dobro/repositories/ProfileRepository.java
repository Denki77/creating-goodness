package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "select p from Profile p where p.user.id = :userId")
    Profile getByUserId(Long userId);
}
