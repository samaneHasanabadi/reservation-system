package ir.azki.reservationsystem.user.application.query.handler;

import ir.azki.reservationsystem.user.application.query.LoginUserQuery;
import ir.azki.reservationsystem.user.infrastructure.security.userdetails.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserHandler {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public String handle(LoginUserQuery command) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(command.username(), command.password()));
        UserDetails user = userDetailsService.loadUserByUsername(command.username());
        return jwtService.generateToken(user);
    }

}
