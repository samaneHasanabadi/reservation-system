package ir.azki.reservationsystem.slot.application.command.handler;

import ir.azki.reservationsystem.slot.domain.SlotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSlotCommandHandler {

    private final SlotRepository slotRepository;

    @Transactional
    @CacheEvict(value = "free-slots", key = "'first'")
    public void handle(Long id) {
        slotRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("slot not found with id : " + id));
        slotRepository.deleteById(id);
    }

}
