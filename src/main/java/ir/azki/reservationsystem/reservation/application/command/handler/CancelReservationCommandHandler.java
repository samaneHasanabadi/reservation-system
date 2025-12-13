package ir.azki.reservationsystem.reservation.application.command.handler;

import ir.azki.reservationsystem.common.exception.EntityNotFoundException;
import ir.azki.reservationsystem.common.exception.ReservationAlreadyCanceledException;
import ir.azki.reservationsystem.common.exception.UnauthorizedReservationAccessException;
import ir.azki.reservationsystem.reservation.domain.Reservation;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import ir.azki.reservationsystem.slot.domain.Slot;
import ir.azki.reservationsystem.slot.domain.SlotRepository;
import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.infrastructure.security.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class CancelReservationCommandHandler {

    private final ReservationRepository reservationRepository;
    private final SlotRepository slotRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Transactional
    @CacheEvict(value = "free-slots", key = "'first'")
    public void handle(Long id) throws AccessDeniedException {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Reservation.class.getSimpleName(), id));
        User currentUser = authenticatedUserProvider.getCurrentUser();
        if (!currentUser.getId().equals(reservation.getUser().getId()))
            throw new UnauthorizedReservationAccessException(id);

        if (reservation.getIsCanceled())
            throw new ReservationAlreadyCanceledException();

        Slot slot = reservation.getSlot();

        slot.setIsReserved(false);
        slotRepository.save(slot);

        reservation.setIsCanceled(true);
        reservationRepository.save(reservation);
    }
}
