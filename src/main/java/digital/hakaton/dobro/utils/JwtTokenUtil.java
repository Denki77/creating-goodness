package digital.hakaton.dobro.utils;

import digital.hakaton.dobro.models.Role;
import digital.hakaton.dobro.models.Token;
import digital.hakaton.dobro.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private int lifetime;


    public String generateToken (User user) {
        return generateToken(
                user.getEmail(),
                user.getId().toString(),
                user.getRoles().stream().map(Role::getCode).collect(Collectors.toList())
        );
    }

    public String generateToken (Token token) {
        return generateToken(token.getEmail(), token.getUserId().toString(), token.getRoles());
    }

    public String generateToken (String email, String userId,  List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", userId);
        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + this.lifetime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Token decode (String token) {
        return new Token(getAllClaimsFromToken(token));
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}