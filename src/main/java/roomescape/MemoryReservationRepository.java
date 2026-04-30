package roomescape;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryReservationRepository implements ReservationRepository {
    List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);


    @Override
    public List<Reservation> read() {
        return List.copyOf(reservations);
    }

    @Override
    public Reservation findById(final long id) {
        return reservations.stream()
                .filter(it -> it.isEqualTo(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void delete(long id) {
        Reservation reservation = reservations.stream()
                .filter(it -> it.isEqualTo(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        reservations.remove(reservation);
    }

    @Override
    public Reservation create(ReservationCreateReqDto reservationRequest
    ) {
        Reservation newReservation = new Reservation(
                index.incrementAndGet(),
                reservationRequest.name(),
                reservationRequest.date(),
                new ReservationTime(reservationRequest.timeId(), null)
        );
        reservations.add(newReservation);
        return newReservation;
    }
}
