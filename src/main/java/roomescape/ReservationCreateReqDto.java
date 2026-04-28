package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationCreateReqDto(String name, LocalDate date, LocalTime time) {

}
