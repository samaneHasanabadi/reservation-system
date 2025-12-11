package ir.azki.reservationsystem.reservation.application.dto;

public record ReservationDTO(Long id, Long slotId, Long userId, Boolean isCanceled) {
    public ReservationDTO(Long id, Long slotId, Long userId, Boolean isCanceled) {
        this.id = id;
        this.slotId = slotId;
        this.userId = userId;
        this.isCanceled = isCanceled;
    }
}
