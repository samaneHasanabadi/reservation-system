package ir.azki.reservationsystem.slot.application.dto;

import java.util.Date;

public record SlotDTO(Date start, Date end, Boolean isReserved) {
}
