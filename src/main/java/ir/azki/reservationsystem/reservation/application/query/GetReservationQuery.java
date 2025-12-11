package ir.azki.reservationsystem.reservation.application.query;

public record GetReservationQuery(Boolean isCanceled, int page, int size) {
    public GetReservationQuery {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        if (size > 100) size = 100;
    }
}
