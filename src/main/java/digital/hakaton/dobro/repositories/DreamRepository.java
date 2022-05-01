package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.Dream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {
    Page<Dream> findAllBy(Pageable pageable);

    Page<Dream> findAllByProfileId(Pageable pageable, Long profileId);

    Optional<Dream> findByIdAndProfileId(Long id, Long profileId);
}