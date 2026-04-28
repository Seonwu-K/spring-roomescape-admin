package roomescape;

import java.util.List;

public interface ReservationRepository {
    List<Reservation> read();
    void removeById(long id);
    void create(ReservationCreateReqDto reqBody);
}
