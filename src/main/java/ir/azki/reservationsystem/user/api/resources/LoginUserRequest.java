package ir.azki.reservationsystem.user.api.resources;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(@NotBlank String username, @NotBlank String password) {
}
