package ir.azki.reservationsystem.slot.domain;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SlotRepository {

    Slot save(Slot slot);
    List<Slot> findOverlappedSlots(Date start, Date end);

    Optional<Slot> findById(Long id);

    void deleteById(Long slotId);

    Page<Slot> findAll(Pageable page);
}
