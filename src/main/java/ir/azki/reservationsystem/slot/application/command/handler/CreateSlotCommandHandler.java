package ir.azki.reservationsystem.slot.application.command.handler;

import ir.azki.reservationsystem.common.exception.SlotEndTimeBeforeStartTimeException;
import ir.azki.reservationsystem.common.exception.TimeSlotsOverlappedException;
import ir.azki.reservationsystem.slot.application.command.CreateSlotCommand;
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
            throw new SlotEndTimeBeforeStartTimeException();

        if (!slotRepository.findOverlappedSlots(command.start(), command.end()).isEmpty())
            throw new TimeSlotsOverlappedException();

        Slot slot = new Slot();
        slot.setStart(command.start());
        slot.setEnd(command.end());
        slotRepository.save(slot);
    }

}
