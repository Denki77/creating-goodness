package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Profile;
import ru.leadersofdigital.dobro.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> getProfileById(Long id);

    Profile getProfileByUser(User user);

    List<Profile> getProfilesByUserId(Long user_id);

    @Query(value = "select r.code from User p left join Role r where p.id = :userId")
    List<String> getRolesByUserId(Long userId);
}
