package ir.azki.reservationsystem.user.api;

import com.google.api.client.auth.oauth2.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.azki.reservationsystem.user.api.resources.LoginUserRequest;
import ir.azki.reservationsystem.user.application.query.LoginUserQuery;
import ir.azki.reservationsystem.user.application.query.handler.LoginUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserQueryController {

    private final LoginUserHandler loginUserHandler;
    private final ConversionService conversionService;

    @PostMapping("/login")
    @Operation(summary = "login the user and return the token")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        LoginUserQuery convert = conversionService.convert(request, LoginUserQuery.class);
        String jwt = loginUserHandler.handle(convert);
        return ResponseEntity.ok(new TokenResponse().setAccessToken(jwt));
    }
}
