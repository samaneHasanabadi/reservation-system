package ir.azki.reservationsystem.slot.application.command.handler;

import ir.azki.reservationsystem.slot.application.command.CreateSlotCommand;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateSlotCommandHandler {

    private final SlotRepository slotRepository;

    @Transactional
    public void handle(Long id, CreateSlotCommand command) {
        Slot slot = slotRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("slot not found with id : " + id));

        if (slot.getIsReserved())
            throw new IllegalArgumentException("Slot is reserved and cannot be updated");

        if (!command.start().before(command.end()))
            throw new IllegalArgumentException("Start date must be before end date");

        List<Slot> overlappedSlots = slotRepository.findOverlappedSlots(command.start(), command.end());
        if (overlappedSlots.stream().anyMatch(s -> !s.getId().equals(id)))
            throw new IllegalArgumentException("time slot is overlapped with available time slots");

        slot.setStart(command.start());
        slot.setEnd(command.end());
        slotRepository.save(slot);
    }

}
