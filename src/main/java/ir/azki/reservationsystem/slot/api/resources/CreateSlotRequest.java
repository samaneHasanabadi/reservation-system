package ir.azki.reservationsystem.slot.api.resources;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateSlotRequest(@NotNull Date start, @NotNull Date end) {
    public CreateSlotRequest {
        if (!start.before(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}
