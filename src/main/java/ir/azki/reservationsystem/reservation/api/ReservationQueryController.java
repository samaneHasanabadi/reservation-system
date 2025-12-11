package ir.azki.reservationsystem.reservation.api;

import ir.azki.reservationsystem.reservation.application.dto.ReservationDTO;
import ir.azki.reservationsystem.reservation.application.query.GetReservationQuery;
import ir.azki.reservationsystem.reservation.application.query.handler.GetReservationQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationQueryController {

    private final GetReservationQueryHandler getReservationQueryHandler;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getSlots(@RequestParam(required = false) Boolean isCanceled,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) throws AccessDeniedException {
        GetReservationQuery getReservationQuery = new GetReservationQuery(isCanceled, page, size);
        return ResponseEntity.ok(getReservationQueryHandler.handle(getReservationQuery));
    }
}
