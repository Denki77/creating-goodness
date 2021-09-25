package ru.leadersofdigital.dobro.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.leadersofdigital.dobro.models.AttributeObject;

public class AttributeSpecification {
    private static Specification<AttributeObject> likeName(String q) {
        return (root, query, builder) -> builder.like(root.get("name"), String.format("%%%s%%", q));
    }

    public static Specification<AttributeObject> likeBuild (String q) {
        return Specification.where(AttributeSpecification.likeName(q));
    }
}