package ir.azki.reservationsystem.slot.application.command.handler;

import ir.azki.reservationsystem.common.exception.EntityNotFoundException;
import ir.azki.reservationsystem.common.exception.SlotAlreadyReservedException;
import ir.azki.reservationsystem.common.exception.SlotEndTimeBeforeStartTimeException;
import ir.azki.reservationsystem.common.exception.TimeSlotsOverlappedException;
import ir.azki.reservationsystem.slot.application.command.CreateSlotCommand;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateSlotCommandHandler {

    private final SlotRepository slotRepository;

    @Transactional
    @CacheEvict(value = "free-slots", key = "'first'")
    public void handle(Long id, CreateSlotCommand command) {
        Slot slot = slotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Slot.class.getSimpleName(), id));

        if (slot.getIsReserved())
            throw new SlotAlreadyReservedException(id);

        if (!command.start().before(command.end()))
            throw new SlotEndTimeBeforeStartTimeException();

        List<Slot> overlappedSlots = slotRepository.findOverlappedSlots(command.start(), command.end());
        if (overlappedSlots.stream().anyMatch(s -> !s.getId().equals(id)))
            throw new TimeSlotsOverlappedException();

        slot.setStart(command.start());
        slot.setEnd(command.end());
        slotRepository.save(slot);
    }

}
