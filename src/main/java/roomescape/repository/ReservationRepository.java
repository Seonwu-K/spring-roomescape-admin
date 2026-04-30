package roomescape.repository;

import java.util.List;
import roomescape.controller.ReservationCreateReqDto;
import roomescape.domain.Reservation;

public interface ReservationRepository {
    List<Reservation> read();
    Reservation findById(long id);
    void delete(long id);
    Reservation create(ReservationCreateReqDto reqBody);
}
