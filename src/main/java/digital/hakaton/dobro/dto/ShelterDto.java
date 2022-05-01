package digital.hakaton.dobro.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterDto {
    private String name;
    private String annotation;
    private String created_at;
    private String updated_at;
    private String description;
    private Long number;
    private Long id;
    private Long city_id;
}