package digital.hakaton.dobro.dto;

import digital.hakaton.dobro.models.Dream;
import digital.hakaton.dobro.models.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DreamDto {

    private Long id;
    private String description;
    private String annotation;
    private Profile profile;

    public DreamDto(Dream dream) {
        this.id = dream.getId();
        this.description = dream.getDescription();
        this.annotation = dream.getAnnotation();
        this.profile = dream.getProfile();
    }
}
