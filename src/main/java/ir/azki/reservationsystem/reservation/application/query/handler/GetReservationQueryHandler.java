package ir.azki.reservationsystem.reservation.application.query.handler;

import ir.azki.reservationsystem.reservation.application.dto.ReservationDTO;
import ir.azki.reservationsystem.reservation.application.query.GetReservationQuery;
import ir.azki.reservationsystem.reservation.domain.ReservationRepository;
import ir.azki.reservationsystem.user.domain.User;
import ir.azki.reservationsystem.user.infrastructure.security.AuthenticatedUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservationQueryHandler {

    private final ReservationRepository reservationRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    public List<ReservationDTO> handle(GetReservationQuery query) {
        User currentUser = authenticatedUserProvider.getCurrentUser();
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return reservationRepository.findByUserAndIsCanceled(currentUser, query.isCanceled(), pageable).stream().toList();
    }
}
