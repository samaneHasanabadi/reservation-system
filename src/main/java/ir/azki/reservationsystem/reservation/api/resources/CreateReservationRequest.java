package ir.azki.reservationsystem.reservation.api.resources;

import jakarta.validation.constraints.NotNull;

public record CreateReservationRequest(@NotNull Long slotId) {
}
