package ir.azki.reservationsystem.slot.application.dto;

import java.util.Date;

public record SlotDTO(Long id, Date start, Date end, Boolean isReserved) {

    public SlotDTO(Long id, Date start, Date end, Boolean isReserved) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.isReserved = isReserved;
    }
}
