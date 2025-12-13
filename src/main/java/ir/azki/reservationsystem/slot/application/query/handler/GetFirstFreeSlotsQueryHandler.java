package ir.azki.reservationsystem.slot.application.query.handler;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.application.query.GetFirstFreeSlotsQuery;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFirstFreeSlotsQueryHandler {

    private final SlotRepository slotRepository;

    @Cacheable(value = "free-slots", key = "'first'")
    public List<SlotDTO> handle(GetFirstFreeSlotsQuery query) {
        return slotRepository.findFirstFreeSlots(Pageable.ofSize(query.limit())).stream().map(a -> new SlotDTO(a.getId(), a.getStart(), a.getEnd(), a.getIsReserved())).collect(Collectors.toList());
    }
}
