package ru.leadersofdigital.dobro.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.leadersofdigital.dobro.models.Token;

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