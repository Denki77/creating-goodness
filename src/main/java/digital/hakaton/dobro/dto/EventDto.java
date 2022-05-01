package digital.hakaton.dobro.dto;

import digital.hakaton.dobro.enums.EventStatus;
import digital.hakaton.dobro.models.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

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
