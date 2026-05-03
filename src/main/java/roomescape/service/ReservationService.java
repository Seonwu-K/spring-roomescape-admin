package roomescape.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.Reservation;
import roomescape.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> read() {
        return reservationRepository.read();
    }

    public Reservation create(final String name, final LocalDate date, final Long timeId) {
        Reservation newReservation = reservationRepository.create(name, date, timeId);
        return reservationRepository.findById(newReservation.getId());
    }

    public void delete(final long id) {
        reservationRepository.delete(id);
    }
}
