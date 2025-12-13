package ir.azki.reservationsystem.slot.application.dto;

import java.util.Date;

public interface SlotProjection {
    Long getId();
    Date getStart();
    Date getEnd();
    Boolean getIsReserved();
}