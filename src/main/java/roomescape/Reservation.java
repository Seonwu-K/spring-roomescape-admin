package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Reservation(long id, ReservationCreateReqDto reqBody) {
        this.id = id;
        this.name = reqBody.name();
        this.date = reqBody.date();
        this.time = reqBody.time();
    }

    public boolean isEqualTo(long id) {
        return this.id == id;
    }
}
