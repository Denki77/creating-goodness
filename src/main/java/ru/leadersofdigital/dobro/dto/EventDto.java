package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.leadersofdigital.dobro.enums.EventStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String comm;
    private EventStatus status;
    private ProfileDto user;
    private LocalDateTime startDate;
    private Integer countDays;
}
