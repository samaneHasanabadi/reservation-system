package ir.azki.reservationsystem.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.azki.reservationsystem.user.api.resources.RegisterUserRequest;
import ir.azki.reservationsystem.user.application.command.RegisterUserCommand;
import ir.azki.reservationsystem.user.application.command.handler.RegisterUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User API")
public class UserCommandController {

    private final RegisterUserHandler registerUserHandler;
    private final ConversionService conversionService;

    @PostMapping("/register")
    @Operation(summary = "register a user")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {
        RegisterUserCommand convert = conversionService.convert(request, RegisterUserCommand.class);
        registerUserHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("User with username " + request.username() + " is successfully created");
    }
}
