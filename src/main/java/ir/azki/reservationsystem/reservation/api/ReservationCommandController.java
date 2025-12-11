package ir.azki.reservationsystem.reservation.api;

import ir.azki.reservationsystem.reservation.api.resources.CreateReservationRequest;
import ir.azki.reservationsystem.reservation.application.command.CreateReservationCommand;
import ir.azki.reservationsystem.reservation.application.command.handler.CancelReservationCommandHandler;
import ir.azki.reservationsystem.reservation.application.command.handler.CreateReservationCommandHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationCommandController {

    private final ConversionService conversionService;
    private final CreateReservationCommandHandler createReservationCommandHandler;
    private final CancelReservationCommandHandler cancelReservationCommandHandler;

    @PostMapping()
    public ResponseEntity<String> register(@Valid @RequestBody CreateReservationRequest request) throws AccessDeniedException {
        CreateReservationCommand convert = conversionService.convert(request, CreateReservationCommand.class);
        createReservationCommandHandler.handle(convert);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation is successfully created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> register(@PathVariable Long id) throws AccessDeniedException {
        cancelReservationCommandHandler.handle(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reservation is canceled successfully");
    }
}
