package ir.azki.reservationsystem.user.infrastructure.security;

import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.infrastructure.security.userdetails.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.security.access.AccessDeniedException;

@Component
public class AuthenticatedUserProvider {

    public User getCurrentUser() throws AccessDeniedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            throw new AccessDeniedException("User is not authenticated");

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal.getDomainUser();
    }

}
