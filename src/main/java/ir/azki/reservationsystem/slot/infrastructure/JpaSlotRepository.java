package ir.azki.reservationsystem.slot.infrastructure;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface JpaSlotRepository extends SlotRepository, JpaRepository<Slot, Long> {

    @Query("select s from Slot s where (s.start < :end and s.start >= :start) or (s.end > :start and s.end <= :end) ")
    List<Slot> findOverlappedSlots(Date start, Date end);

    @Query("select new ir.azki.reservationsystem.slot.application.dto.SlotDTO(s.id, s.start, s.end, s.isReserved) from Slot s where :isReserved is null or s.isReserved = :isReserved")
    Page<SlotDTO> findAll(Boolean isReserved, Pageable page);

    @Query("select new ir.azki.reservationsystem.slot.application.dto.SlotDTO(s.id, s.start, s.end, s.isReserved) from Slot s where s.isReserved = false order by s.start asc")
    List<SlotDTO> findFirstFreeSlot(Limit of);

}
