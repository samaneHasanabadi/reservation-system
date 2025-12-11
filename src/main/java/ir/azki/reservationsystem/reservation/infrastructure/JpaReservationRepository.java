package ir.azki.reservationsystem.reservation.infrastructure;

import ir.azki.reservationsystem.reservation.application.dto.ReservationDTO;
import ir.azki.reservationsystem.reservation.domain.Reservation;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import ir.azki.reservationsystem.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaReservationRepository extends ReservationRepository, JpaRepository<Reservation, Long> {

    @Query("select new ir.azki.reservationsystem.reservation.application.dto.ReservationDTO(r.id, r.slot.id, r.user.id, r.isCanceled) from Reservation r where r.user = :user" +
            " and (:isCanceled is null or r.isCanceled = :isCanceled)")
    Page<ReservationDTO> findByUserAndIsCanceled(User user, Boolean isCanceled, Pageable pageable);
}
