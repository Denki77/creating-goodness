package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Dream;

import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<Dream, Long> {
    Page<Dream> findAllBy(Pageable pageable);

    Page<Dream> findAllByProfileId(Pageable pageable, Long profileId);

    Optional<Dream> findByIdAndProfileId(Long id, Long profileId);
}