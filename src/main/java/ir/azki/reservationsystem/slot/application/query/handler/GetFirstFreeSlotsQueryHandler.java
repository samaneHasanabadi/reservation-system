package ir.azki.reservationsystem.slot.application.query.handler;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.application.query.GetFirstFreeSlotsQuery;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFirstFreeSlotsQueryHandler {

    private final SlotRepository slotRepository;

    public List<SlotDTO> handle(GetFirstFreeSlotsQuery query) {
        return slotRepository.findFirstFreeSlots(Limit.of(query.limit()));
    }
}
