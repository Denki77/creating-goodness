package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Tag;

@Repository
public interface TagsRepository extends JpaRepository<Tag, Long> {
}
