package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.leadersofdigital.dobro.enums.EventStatus;
import ru.leadersofdigital.dobro.models.Event;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private String annotation;
    private EventStatus status;
    private ProfileDto user;
    private LocalDateTime startDate;
    private Integer countDays;

    public EventDto(Event event) {
        this.id = event.getId();
        this.annotation = event.getAnnotation();
        this.name = event.getName();
        this.status = event.getStatus();
        this.startDate = event.getStartDate();
        this.status = event.getStatus();
    }
}
