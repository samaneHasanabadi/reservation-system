package ir.azki.reservationsystem.common.exception;

public class UnauthorizedReservationAccessException extends RuntimeException {
    public UnauthorizedReservationAccessException(Long slotId) {
        super("You are not authorized to access reservation with id: " + slotId);
    }
}
