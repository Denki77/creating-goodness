package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.EventDto;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Event;
import ru.leadersofdigital.dobro.repositories.EventRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final static Function<Event, EventDto> functionToDto = event -> {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setComm(event.getComm());
        dto.setCountDays(event.getCountDays());
        dto.setStatus(event.getStatus());
        dto.setStartDate(event.getStartDate());
        return dto;
    };

    public List<EventDto> getByMonth(Integer dayOfMonth) {
        return eventRepository.getAllOfMonth(dayOfMonth)
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }

    public EventDto getById(Long id){
        return functionToDto.apply(eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotFound role id " + id)));
    }
}
