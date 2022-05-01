package digital.hakaton.dobro.models;


import io.jsonwebtoken.Claims;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Data
public class Token {

    private Long userId;
    private String email;
    private List<String> roles;
    private Date expiredDate;

    public Token(@NotNull Claims claims) {
        this.userId = Long.valueOf((String) claims.get("user_id"));
        this.roles = getClaimsValue(claims, (Function<Claims, List<String>>) c -> c.get("roles", List.class));
        this.email = claims.getSubject();
        this.expiredDate = claims.getExpiration();
    }

    public long remainingTime () {
        long difference = expiredDate.getTime() - new Date().getTime();
        return difference / 1000 / 60;
    }

    private <T> T getClaimsValue(Claims claims, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }
}