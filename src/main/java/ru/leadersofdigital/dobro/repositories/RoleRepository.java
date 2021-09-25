package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.leadersofdigital.dobro.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
