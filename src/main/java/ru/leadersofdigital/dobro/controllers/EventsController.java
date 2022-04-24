package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.EventDto;
import ru.leadersofdigital.dobro.services.EventService;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventsController {
    private final EventService eventService;

    /**
     * Поиск события по его id
     */
    @GetMapping("/{id}")
    public EventDto getById(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    /**
     * Поиск события по месяцу и году
     */
    @GetMapping("/month")
    public List<EventDto> getEventsByMonthAndYear(
            @NotNull @RequestParam("month") Integer month,
            @NotNull @RequestParam("year") Integer year
    ) {
        return eventService.getByMonth(month, year);
    }

    /**
     * Поиск событий по двум датам
     * Params:
     * text – the text to parse such as "2007-12-03T10:15:30", not null
     */
    @GetMapping("/date/between")
    public List<EventDto> getEventsByDateBetween(@RequestParam("start") String start, @RequestParam("end") String end) throws ParseException {
        return eventService.getByDateBetween(start, end);
    }

    /**
     * Поиск событий по профилю пользователя создавшим его
     */
    @GetMapping("/user/{id}")
    public List<EventDto> getEventsByUser(@PathVariable("id") Long id) {
        return eventService.getByUser(id);
    }

    /**
     * Поиск событий в конкретный день
     */
    @GetMapping("/date")
    public List<EventDto> getEventsByDate(@RequestParam("day") String day) throws ParseException {
        return this.getEventsByDateBetween(day, day);
    }
}
