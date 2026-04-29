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
    public void delete(long id) {
        Reservation reservation = reservations.stream()
                .filter(it -> it.isEqualTo(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        reservations.remove(reservation);
    }

    @Override
    public Reservation create(ReservationCreateReqDto reqBody) {
        Reservation newReservation = new Reservation(
                index.incrementAndGet(),
                reqBody.name(),
                reqBody.date(),
                reqBody.time()
            );
        reservations.add(newReservation);
        return newReservation;
    }
}
