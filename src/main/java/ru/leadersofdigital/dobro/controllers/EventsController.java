package ru.leadersofdigital.dobro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.leadersofdigital.dobro.dto.EventDto;
import ru.leadersofdigital.dobro.services.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventsController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public EventDto getById(@PathVariable("id") Long id){
        return eventService.getById(id);
    }

    @GetMapping("/date")
    public List<EventDto> getEventsByDate(@RequestParam("month") Integer monthId){
        return eventService.getByMonth(monthId);
    }
}
