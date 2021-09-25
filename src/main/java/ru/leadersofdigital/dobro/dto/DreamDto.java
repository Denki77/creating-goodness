package ru.leadersofdigital.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.leadersofdigital.dobro.models.Dream;
import ru.leadersofdigital.dobro.models.User;

@Data
@NoArgsConstructor
public class DreamDto {

    private Long id;
    private String description;
    private String annotation;
    private User user;

    public DreamDto(Dream dream) {
        this.id = dream.getId();
        this.description = dream.getDescription();
        this.annotation = dream.getAnnotation();
        this.user = dream.getUser();
    }
}
