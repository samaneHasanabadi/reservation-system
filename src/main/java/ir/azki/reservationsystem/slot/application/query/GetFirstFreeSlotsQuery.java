package ir.azki.reservationsystem.slot.application.query;

import java.io.Serializable;

public record GetFirstFreeSlotsQuery(int limit) implements Serializable {
    public GetFirstFreeSlotsQuery {
        if (limit <= 0) limit = 1;
    }
}
