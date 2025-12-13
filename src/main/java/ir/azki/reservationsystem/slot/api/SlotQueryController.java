package ir.azki.reservationsystem.slot.api;

import ir.azki.reservationsystem.slot.application.dto.SlotDTO;
import ir.azki.reservationsystem.slot.application.query.GetFirstFreeSlotsQuery;
import ir.azki.reservationsystem.slot.application.query.GetSlotQuery;
import ir.azki.reservationsystem.slot.application.query.handler.GetFirstFreeSlotsQueryHandler;
import ir.azki.reservationsystem.slot.application.query.handler.GetSlotQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/slot")
public class SlotQueryController {

    private final GetSlotQueryHandler getSlotQueryHandler;
    private final GetFirstFreeSlotsQueryHandler getFirstFreeSlotsQueryHandler;

    @GetMapping
    public ResponseEntity<List<SlotDTO>> getSlots(@RequestParam(required = false) Boolean isReserved,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        GetSlotQuery getSlotQuery = new GetSlotQuery(isReserved, page, size);
        return ResponseEntity.ok(getSlotQueryHandler.handle(getSlotQuery));
    }

    @GetMapping("/firstFree")
    public ResponseEntity<List<SlotDTO>> getFirstFreeSlots(@RequestParam(defaultValue = "1") int limit) {
        GetFirstFreeSlotsQuery query = new GetFirstFreeSlotsQuery(limit);
        return ResponseEntity.ok(getFirstFreeSlotsQueryHandler.handle(query));
    }
}
