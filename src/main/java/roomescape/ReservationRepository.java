package roomescape;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public interface ReservationRepository {
    List<Reservation> findReservations();
    void removeByid(AtomicLong id);
    void create(ReservationCreateReqDto reqBody);
}
