package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.AttributeObject;
import digital.hakaton.dobro.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long>, JpaSpecificationExecutor<AttributeObject> {

    List<Shelter> findByNameStartsWith(String q);
}
