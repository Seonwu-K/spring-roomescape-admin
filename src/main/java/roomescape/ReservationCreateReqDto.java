package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public record ReservationCreateReqDto(AtomicLong id, String name, LocalDate date, LocalTime time) {

}
