package ir.azki.reservationsystem.slot.application.query;

public record GetFirstFreeSlotsQuery(int limit) {
    public GetFirstFreeSlotsQuery {
        if (limit <= 0) limit = 1;
    }
}
