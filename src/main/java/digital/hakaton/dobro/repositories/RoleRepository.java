package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.AttributeObject;
import digital.hakaton.dobro.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<AttributeObject> {
    Optional<Role> findByName(String roleName);

    List<Role> getRolesByStatusIs(Integer status);

    Role getRoleByCode(String code);

    List<Role> getUserRolesById(Long id);
}