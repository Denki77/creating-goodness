package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.AttributeObject;
import ru.leadersofdigital.dobro.models.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<AttributeObject> {
    Optional<Role> findByName(String roleName);

    List<Role> getRolesByStatusGreaterThanEqual(Integer status);

    Role getRoleByCode(String code);
}
