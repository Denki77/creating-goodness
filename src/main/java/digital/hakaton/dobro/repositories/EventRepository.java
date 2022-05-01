package digital.hakaton.dobro.repositories;

import digital.hakaton.dobro.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("select e from Event e where month(e.startDate) = :month and year(e.startDate) = :year")
    List<Event> getAllOfMonth(int month, int year);

    List<Event> getEventsByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Event> getEventsByProfile_Id(Long profile_id);

    Page<Event> findAllBy(Pageable pageable);
}
