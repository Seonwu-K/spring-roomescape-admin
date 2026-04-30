package roomescape;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> read();
    Reservation findById(long id);
    void delete(long id);
    Reservation create(ReservationCreateReqDto reqBody);
}
