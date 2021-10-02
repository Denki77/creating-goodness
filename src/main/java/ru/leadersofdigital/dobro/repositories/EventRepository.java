package ru.leadersofdigital.dobro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.leadersofdigital.dobro.models.Event;

import java.time.Month;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    List<Event> findAllByStartDate_Month(Month startDate_month);
//
//    List<Event> findAllByStartDate_MonthValue(Integer startDate_monthValue);

    @Query("select e from Event e where month(e.startDate) = :month")
    List<Event> getAllOfMonth(int month);
}
