package ir.azki.reservationsystem.reservation.api;

import jakarta.validation.constraints.NotNull;

public record CreateReservationRequest(@NotNull Long slotId) {
}
