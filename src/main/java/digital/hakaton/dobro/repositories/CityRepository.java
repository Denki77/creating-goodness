package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.AttributeObject;
import digital.hakaton.dobro.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> , JpaSpecificationExecutor<AttributeObject> {

    List<City> findByNameStartsWith(String q);
}
