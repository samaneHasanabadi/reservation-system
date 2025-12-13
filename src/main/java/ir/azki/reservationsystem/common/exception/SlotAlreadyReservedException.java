package ir.azki.reservationsystem.common.exception;

public class SlotAlreadyReservedException extends RuntimeException {
    public SlotAlreadyReservedException(Long slotId) {
        super("Slot with id: " + slotId + " is already reserved");
    }
}
