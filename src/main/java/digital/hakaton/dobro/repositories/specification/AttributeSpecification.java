package digital.hakaton.dobro.repositories.specification;

import digital.hakaton.dobro.models.AttributeObject;
import org.springframework.data.jpa.domain.Specification;

public class AttributeSpecification {
    private static Specification<AttributeObject> likeName(String q) {
        return (root, query, builder) -> builder.like(root.get("name"), String.format("%%%s%%", q));
    }

    public static Specification<AttributeObject> likeBuild (String q) {
        return Specification.where(AttributeSpecification.likeName(q));
    }
}