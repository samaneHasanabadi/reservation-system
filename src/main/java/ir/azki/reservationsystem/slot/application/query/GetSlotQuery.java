package ir.azki.reservationsystem.slot.application.query;

public record GetSlotQuery(Boolean isReserved, int page, int size) {
    public GetSlotQuery {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (size > 100) size = 100;
    }
}
