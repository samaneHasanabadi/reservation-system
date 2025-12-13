package ir.azki.reservationsystem.reservation.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.azki.reservationsystem.reservation.api.resources.CreateReservationRequest;
import ir.azki.reservationsystem.reservation.application.command.CreateReservationCommand;
import ir.azki.reservationsystem.reservation.application.command.handler.CancelReservationCommandHandler;
import ir.azki.reservationsystem.reservation.application.command.handler.CreateReservationCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
@Tag(name = "Reservation API")
public class ReservationCommandController {

    private final ConversionService conversionService;
    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final CancelReservationCommandHandler cancelReservationCommandHandler;

    @PostMapping()
    @Operation(summary = "reserve a slot for the user")
    public ResponseEntity<String> reserve(@Valid @RequestBody CreateReservationRequest request) {
        CreateReservationCommand convert = conversionService.convert(request, CreateReservationCommand.class);
        createReservationCommandHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation is successfully created");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "cancel a reservation for the user")
    public ResponseEntity<String> cancel(@PathVariable Long id) {
        cancelReservationCommandHandler.handle(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation is canceled successfully");
    }
}
