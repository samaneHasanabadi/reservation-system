package ir.azki.reservationsystem.reservation.domain;

import ir.azki.reservationsystem.reservation.application.dto.ReservationDTO;
import ir.azki.reservationsystem.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long id);

    Page<ReservationDTO> findByUserAndIsCanceled(User user, Boolean isCanceled, Pageable pageable);

}
