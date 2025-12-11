package ir.azki.reservationsystem.slot.domain;

import java.util.Date;
import java.util.List;

public interface SlotRepository {

    Slot save(Slot slot);
    List<Slot> findOverlappedSlots(Date start, Date end);
}
