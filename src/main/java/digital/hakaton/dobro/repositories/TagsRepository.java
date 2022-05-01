package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
}
