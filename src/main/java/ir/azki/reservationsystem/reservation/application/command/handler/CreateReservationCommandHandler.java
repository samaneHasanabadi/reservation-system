package ir.azki.reservationsystem.reservation.application.command.handler;

import ir.azki.reservationsystem.common.exception.EntityNotFoundException;
import ir.azki.reservationsystem.common.exception.SlotAlreadyReservedException;
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

@Service
@RequiredArgsConstructor
public class CreateReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    @CacheEvict(value = "free-slots", key = "'first'")
    public void handle(CreateReservationCommand command) {
        Slot slot = slotRepository.findById(command.slotId()).orElseThrow(() -> new EntityNotFoundException(Slot.class.getSimpleName(), command.slotId()));

        if (slot.getIsReserved())
            throw new SlotAlreadyReservedException(command.slotId());

        Reservation reservation = new Reservation();
        reservation.setSlot(slot);
        reservation.setUser(authenticatedUserProvider.getCurrentUser());

        slot.setIsReserved(true);
        slotRepository.save(slot);

        reservationRepository.save(reservation);
    }
}
