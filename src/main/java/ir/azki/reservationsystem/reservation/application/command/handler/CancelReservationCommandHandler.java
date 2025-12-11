package ir.azki.reservationsystem.reservation.application.command.handler;

import ir.azki.reservationsystem.reservation.domain.Reservation;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    public void handle(Long id) throws AccessDeniedException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("reservation not found with id : " + id));
        User currentUser = authenticatedUserProvider.getCurrentUser();
        if (!currentUser.getId().equals(reservation.getUser().getId()))
            throw new AccessDeniedException("you have no permission to cancel this reservation");

        if (reservation.getIsCanceled())
            throw new IllegalArgumentException("reservation is canceled already");

        Slot slot = reservation.getSlot();

        slot.setIsReserved(false);
        slotRepository.save(slot);

        reservation.setIsCanceled(true);
        reservationRepository.save(reservation);
    }
}
