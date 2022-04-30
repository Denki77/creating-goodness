package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Role;
import ru.leadersofdigital.dobro.models.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Page<User> findByRoles(Role role, Pageable pageable);

    User findOneByUsername(String username);

    User getUserByEmail(String email);

    List<String> getRolesById(Long id);
}