package ru.leadersofdigital.dobro.dtos;

import lombok.Data;
import ru.leadersofdigital.dobro.models.AttributeObject;

@Data
public class AttributeDto {

    private Long id;
    private String name;

    public AttributeDto(AttributeObject attributes) {
        this.id = attributes.getId();
        this.name = attributes.getName();
    }

    public AttributeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}