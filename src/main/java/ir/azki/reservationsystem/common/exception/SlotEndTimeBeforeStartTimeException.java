package ir.azki.reservationsystem.common.exception;

public class SlotEndTimeBeforeStartTimeException extends RuntimeException {
    public SlotEndTimeBeforeStartTimeException() {
        super("Start date must be before end date");
    }
}
