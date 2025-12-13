package ir.azki.reservationsystem.common.exception;

public class TimeSlotsOverlappedException extends RuntimeException {
    public TimeSlotsOverlappedException() {
        super("Time slot is overlapped with available time slots");
    }
}
