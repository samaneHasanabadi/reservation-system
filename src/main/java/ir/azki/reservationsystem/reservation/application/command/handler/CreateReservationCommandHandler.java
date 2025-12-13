package ir.azki.reservationsystem.reservation.application.command.handler;

import ir.azki.reservationsystem.reservation.application.command.CreateReservationCommand;
import ir.azki.reservationsystem.reservation.domain.Reservation;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import ir.azki.reservationsystem.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CreateReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    @CacheEvict(value = "free-slots", key = "'first'")
    public void handle(CreateReservationCommand command) throws AccessDeniedException {
        Slot slot = slotRepository.findById(command.slotId()).orElseThrow(() -> new IllegalArgumentException("slot not found with id : " + command.slotId()));

        if (slot.getIsReserved())
            throw new RuntimeException("Slot is reserved already");

        Reservation reservation = new Reservation();
        reservation.setSlot(slot);
        reservation.setUser(authenticatedUserProvider.getCurrentUser());

        slot.setIsReserved(true);
        slotRepository.save(slot);

        reservationRepository.save(reservation);
    }
}
