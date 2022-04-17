package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Page<User> findByRoles(Role role, Pageable pageable);

    User findOneByUsername(String username);

    User getUserByUsername(String name);
}