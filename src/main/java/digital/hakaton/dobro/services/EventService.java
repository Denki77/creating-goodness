package digital.hakaton.dobro.services;

import digital.hakaton.dobro.dto.EventDto;
import digital.hakaton.dobro.dto.ProfileDto;
import digital.hakaton.dobro.errorHandling.ResourceNotFoundException;
import digital.hakaton.dobro.models.Event;
import digital.hakaton.dobro.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        dto.setDescription(event.getDescription());
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

    public EventDto getById(Long id) {
        return functionToDto.apply(eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("NotFound role id " + id)));
    }

    public List<EventDto> getByDateBetween(String start, String end) {
        LocalDateTime startFormat = LocalDateTime.of(LocalDate.parse(this.repairDateSting(start)), LocalTime.MIN);
        LocalDateTime endFormat = LocalDateTime.of(LocalDate.parse(this.repairDateSting(end)), LocalTime.MAX);
        return eventRepository
                .getEventsByStartDateBetween(
                        startFormat,
                        endFormat
                )
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }

    private String repairDateSting(String start) {
        if (start.length() != 10) {
            if (start.length() == 9) {
                return start.substring(0, 5) + '0' + start.substring(5);
            }
            return "1970-01-01";
        }
        return start;
    }

    public List<EventDto> getByUser(Long id) {
        return eventRepository.getEventsByProfile_Id(id)
                .stream()
                .map(functionToDto)
                .collect(Collectors.toList());
    }

    public Page<Event> findPage(int page, int pageSize) {
        return eventRepository.findAll(PageRequest.of(page, pageSize));
    }
}