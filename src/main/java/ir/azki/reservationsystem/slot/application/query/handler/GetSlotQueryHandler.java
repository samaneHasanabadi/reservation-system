package ir.azki.reservationsystem.slot.application.query.handler;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.application.query.GetSlotQuery;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSlotQueryHandler {

    private final SlotRepository slotRepository;

    public List<SlotDTO> handle(GetSlotQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return slotRepository.findAll(pageable).stream()
                .filter(s -> query.isReserved() == null || s.getIsReserved() != null && s.getIsReserved().equals(query.isReserved()))
                .map(s -> new SlotDTO(s.getStart(), s.getEnd(), s.getIsReserved()))
                .collect(Collectors.toList());
    }
}
