package ru.leadersofdigital.dobro.configs;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.leadersofdigital.dobro.models.Token;
import ru.leadersofdigital.dobro.utils.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Integer MIN_TIME_UPDATE_TOKEN = 5;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader("Authorization") != null) {
            // ** FORMATTED: token [----] ** //
            String token = request.getHeader("Authorization").substring(5);
            try {
                Token tokenObjects = jwtTokenUtil.decode(token);

                if (tokenObjects.remainingTime() < MIN_TIME_UPDATE_TOKEN) {
                    String newToken = jwtTokenUtil.generateToken(tokenObjects);
                    // todo обновим токен через сессию
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(tokenObjects.getEmail(), null, tokenObjects.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                auth.setDetails(tokenObjects.getUserId());
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (JwtException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }


}
