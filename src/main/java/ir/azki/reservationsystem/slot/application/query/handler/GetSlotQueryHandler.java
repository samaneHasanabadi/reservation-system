package ir.azki.reservationsystem.slot.application.query.handler;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.application.query.GetSlotQuery;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSlotQueryHandler {

    private final SlotRepository slotRepository;

    public List<SlotDTO> handle(GetSlotQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return slotRepository.findAll(query.isReserved(), pageable).stream().toList();
    }
}
