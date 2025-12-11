package ir.azki.reservationsystem.reservation.infrastructure;

import ir.azki.reservationsystem.reservation.domain.Reservation;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReservationRepository extends ReservationRepository, JpaRepository<Reservation, Long> {
}
