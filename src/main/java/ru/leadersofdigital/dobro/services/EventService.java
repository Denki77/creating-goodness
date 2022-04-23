package ru.leadersofdigital.dobro.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.dto.EventDto;
import ru.leadersofdigital.dobro.dto.ProfileDto;
import ru.leadersofdigital.dobro.dto.UserDto;
import ru.leadersofdigital.dobro.error_handling.ResourceNotFoundException;
import ru.leadersofdigital.dobro.models.Event;
import ru.leadersofdigital.dobro.repositories.EventRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
        dto.setComm(event.getDescription());
        dto.setCountDays(event.getCountDays());
        dto.setStatus(event.getStatus());
        dto.setStartDate(event.getStartDate());
        ProfileDto user = new ProfileDto();
        user.setUserId(event.getProfile().getId());
        user.setLastname(event.getProfile().getLastname());
        user.setFirstname(event.getProfile().getFirstname());
        dto.setUser(user);
        return dto;
    };

    public List<EventDto> getByMonth(Integer month, Integer year) {
        return eventRepository.getAllOfMonth(month, year)
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }

    public EventDto getById(Long id){
        return functionToDto.apply(eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotFound role id " + id)));
    }

    public List<EventDto> getByDateBetween(String start, String end) {
        return eventRepository.getEventsByStartDateBetween(LocalDateTime.parse(start), LocalDateTime.parse(end))
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }

    public List<EventDto> getByUser(Long id){
        return eventRepository.getEventsByProfile_Id(id)
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }
}