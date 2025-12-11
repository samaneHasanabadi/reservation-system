package ir.azki.reservationsystem.slot.application;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateSlotCommand(@NotNull Date start, @NotNull Date end) {
}
