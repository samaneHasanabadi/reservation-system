package ir.azki.reservationsystem.reservation.domain;

import java.util.Optional;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(Long id);


}
