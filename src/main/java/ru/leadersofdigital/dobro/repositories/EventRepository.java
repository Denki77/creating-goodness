package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where month(e.startDate) = :month and year(e.startDate) = :year")
    List<Event> getAllOfMonth(int month, int year);

    List<Event> getEventsByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Event> getEventsByProfile_Id(Long profile_id);
}
