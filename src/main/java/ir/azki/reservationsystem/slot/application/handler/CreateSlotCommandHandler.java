package ir.azki.reservationsystem.slot.application.handler;

import ir.azki.reservationsystem.slot.application.CreateSlotCommand;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateSlotCommandHandler {

    private final SlotRepository slotRepository;

    @Transactional
    public void handle(CreateSlotCommand command) {
        if (!command.start().before(command.end()))
            throw new IllegalArgumentException("Start date must be before end date");

        if (!slotRepository.findOverlappedSlots(command.start(), command.end()).isEmpty())
            throw new IllegalArgumentException("time slot is overlapped with available time slots");

        Slot slot = new Slot();
        slot.setStart(command.start());
        slot.setEnd(command.end());
        slotRepository.save(slot);
    }

}
