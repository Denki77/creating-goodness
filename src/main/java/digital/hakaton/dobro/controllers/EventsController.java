package digital.hakaton.dobro.controllers;

import digital.hakaton.dobro.dto.EventDto;
import digital.hakaton.dobro.models.Event;
import digital.hakaton.dobro.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Events", description = "The event API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventsController {
    private final EventService eventService;

    @Operation(summary = "Get events per pages", tags = "Events")
    @GetMapping
    public Page<EventDto> getAllDreams(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "count", defaultValue = "10") int count
    ) {
        Page<Event> eventsPage = eventService.findPage(page - 1, count);
        return new PageImpl<>(eventsPage.getContent().stream().map(EventDto::new).collect(Collectors.toList()), eventsPage.getPageable(), eventsPage.getTotalElements());
    }

    /**
     * Поиск события по его id
     */
    @Operation(summary = "Get events by id", tags = "Events")
    @GetMapping("/{id}")
    public EventDto getById(@PathVariable("id") Long id) {
        return eventService.getById(id);
    }

    /**
     * Поиск события по месяцу и году
     */
    @Operation(summary = "Get list of events by month and year", tags = "Events")
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
    @Operation(summary = "Get events between two dates", tags = "Events")
    @GetMapping("/date/between")
    public List<EventDto> getEventsByDateBetween(
            @RequestParam("start") String start,
            @RequestParam("end") String end
    ) throws ParseException {
        return eventService.getByDateBetween(start, end);
    }

    /**
     * Поиск событий по профилю пользователя создавшим его
     */
    @Operation(summary = "Get events by user id", tags = "Events")
    @GetMapping("/user/{id}")
    public List<EventDto> getEventsByUser(@PathVariable("id") Long id) {
        return eventService.getByUser(id);
    }

    /**
     * Поиск событий в конкретный день
     */
    @Operation(summary = "Get events by date", tags = "Events")
    @GetMapping("/date")
    public List<EventDto> getEventsByDate(@RequestParam("day") String day) throws ParseException {
        return this.getEventsByDateBetween(day, day);
    }
}
