package roomescape;

import java.time.LocalTime;

public class ReservationTime {
    private long id;
    private LocalTime startAt;

    public long getId() {
        return id;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public ReservationTime() {

    }

    public ReservationTime(long id, LocalTime startAt) {
        this.id = id;
        this.startAt = startAt;
    }
}
