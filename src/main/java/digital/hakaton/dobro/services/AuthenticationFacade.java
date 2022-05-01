package digital.hakaton.dobro.services;

import digital.hakaton.dobro.models.Token;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getUsername() {
        return this.getAuthentication().getName();
    }

    public Long getUserId() {
        Token token = (Token) this.getAuthentication().getDetails();
        return token.getUserId();
    }
}