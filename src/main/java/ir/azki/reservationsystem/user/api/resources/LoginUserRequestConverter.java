package ir.azki.reservationsystem.user.api.resources;

import ir.azki.reservationsystem.user.application.query.LoginUserQuery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LoginUserRequestConverter implements Converter<LoginUserRequest, LoginUserQuery> {
    @Override
    public LoginUserQuery convert(LoginUserRequest source) {
        return new LoginUserQuery(source.username(), source.password());
    }
}
