package roomescape;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> read();
    void delete(long id);
    Reservation create(ReservationCreateReqDto reqBody);
}
