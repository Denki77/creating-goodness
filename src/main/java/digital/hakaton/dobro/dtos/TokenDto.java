package digital.hakaton.dobro.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDto {
    private String token;
    private Boolean isNewUser;


    public TokenDto (String token) {
        this.token = token;
    }

    public TokenDto (String token, Boolean isNewUser) {
        this.token = token;
        this.isNewUser = isNewUser;
    }
}
