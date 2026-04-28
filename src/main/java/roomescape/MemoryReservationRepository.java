package roomescape;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryReservationRepository implements ReservationRepository {
    List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(1);


    @Override
    public List<Reservation> read() {
        return List.copyOf(reservations);
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public void create(ReservationCreateReqDto reqBody) {
        Reservation reservation = new Reservation(index.incrementAndGet(), reqBody);
        reservations.add(reservation);
    }
}
