package roomescape;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Reservation(long id, String name, LocalDate date, LocalTime time) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    private void validateName(final String name) {
        if(name.length() >= 10) {
            throw new IllegalArgumentException("[ERROR] 잘못된 이름 입력입니다.");
        }
    }

    public boolean isEqualTo(long id) {
        return this.id == id;
    }

    public long getId() {
        return this.id;
    }
}
