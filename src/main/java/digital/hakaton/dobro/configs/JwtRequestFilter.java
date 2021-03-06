package digital.hakaton.dobro.configs;

import digital.hakaton.dobro.models.Token;
import digital.hakaton.dobro.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Integer MIN_TIME_UPDATE_TOKEN = 200;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getHeader("Authorization") != null) {
            // ** FORMATTED: token [----] ** //
            String token = request.getHeader("Authorization").substring(6);
            try {
                Token tokenObjects = jwtTokenUtil.decode(token);

                if (tokenObjects.remainingTime() < MIN_TIME_UPDATE_TOKEN && request.getHeader("Refresh-Token") != null) {
                    String newToken = jwtTokenUtil.generateToken(tokenObjects);
                    response.setHeader("Refresh-Token", newToken);
                }

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        tokenObjects.getEmail(),
                        null,
                        tokenObjects.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                auth.setDetails(tokenObjects);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (ExpiredJwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (JwtException e) {
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}