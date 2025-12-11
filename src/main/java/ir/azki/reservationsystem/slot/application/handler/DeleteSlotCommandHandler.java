package ir.azki.reservationsystem.slot.application.handler;

import ir.azki.reservationsystem.slot.domain.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSlotCommandHandler {

    private final SlotRepository slotRepository;

    @Transactional
    public void handle(Long id) {
        slotRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("slot not found with id : " + id));
        slotRepository.deleteById(id);
    }

}
