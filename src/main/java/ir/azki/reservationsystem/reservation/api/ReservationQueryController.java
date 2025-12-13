package ir.azki.reservationsystem.reservation.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.azki.reservationsystem.reservation.application.dto.ReservationDTO;
import ir.azki.reservationsystem.reservation.application.query.GetReservationQuery;
import ir.azki.reservationsystem.reservation.application.query.handler.GetReservationQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
@Tag(name = "Reservation API")
public class ReservationQueryController {

    private final GetReservationQueryHandler getReservationQueryHandler;

    @GetMapping
    @Operation(summary = "get free slots")
    public ResponseEntity<List<ReservationDTO>> getSlots(@RequestParam(required = false) Boolean isCanceled,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        GetReservationQuery getReservationQuery = new GetReservationQuery(isCanceled, page, size);
        return ResponseEntity.ok(getReservationQueryHandler.handle(getReservationQuery));
    }
}
