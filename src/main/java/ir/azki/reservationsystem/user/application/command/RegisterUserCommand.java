package ir.azki.reservationsystem.user.application.command;

import jakarta.validation.constraints.NotBlank;

public record RegisterUserCommand(
        @NotBlank
        String username,
        @NotBlank
        String password,
        String email
) {
}
