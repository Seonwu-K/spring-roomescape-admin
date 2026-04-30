package roomescape;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcReservationRepository implements ReservationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getDate("date").toLocalDate(),
            mapReservationTime(resultSet)
    );

    public JdbcReservationRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Reservation> read() {
        String sql = """
                SELECT r.id,
                       r.name,
                       r.date,
                       rt.id AS time_id,
                       rt.start_at
                FROM reservation AS r
                JOIN reservation_time AS rt ON r.time_id = rt.id
                """;

        return jdbcTemplate.query(sql, reservationRowMapper);
    }

    @Override
    public Reservation findById(final long id) {
        String sql = """
                SELECT r.id,
                       r.name,
                       r.date,
                       rt.id AS time_id,
                       rt.start_at
                FROM reservation AS r
                JOIN reservation_time AS rt ON r.time_id = rt.id
                WHERE r.id = ?
                """;

        return jdbcTemplate.queryForObject(sql, reservationRowMapper, id);
    }

    @Override
    public void delete(final long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Reservation create(final ReservationCreateReqDto reservationRequest) {
        String sql = "INSERT INTO reservation (name, date, time_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Long timeId = validateTimeId(reservationRequest.timeId());

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, reservationRequest.name());
            preparedStatement.setDate(2, Date.valueOf(reservationRequest.date()));
            preparedStatement.setLong(3, timeId);
            return preparedStatement;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalStateException("[ERROR] 예약 ID를 생성하지 못했습니다.");
        }

        return new Reservation(
                key.longValue(),
                reservationRequest.name(),
                reservationRequest.date(),
                null
        );
    }

    private Long validateTimeId(final Long timeId) {
        if (timeId == null) {
            throw new IllegalArgumentException("[ERROR] 예약 시간 ID는 필수입니다.");
        }

        Integer count = jdbcTemplate.queryForObject(
                "SELECT count(1) FROM reservation_time WHERE id = ?",
                Integer.class,
                timeId
        );
        if (count == null || count == 0) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 예약 시간입니다.");
        }

        return timeId;
    }

    private ReservationTime mapReservationTime(final ResultSet resultSet) throws SQLException {
        Time startAt = resultSet.getTime("start_at");
        return new ReservationTime(
                resultSet.getLong("time_id"),
                startAt.toLocalTime()
        );
    }
}
