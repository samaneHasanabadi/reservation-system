package ir.azki.reservationsystem.common.exception;

public class ReservationAlreadyCanceledException extends RuntimeException {
    public ReservationAlreadyCanceledException() {
        super("Reservation is canceled already");
    }
}
