package roomescape;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository = new MemoryReservationRepository();

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> read() {
        return ResponseEntity.ok().body(reservationRepository.read());
    }

    @PostMapping("/reservations")
    public ResponseEntity<Map<String, Long>> create(@RequestBody ReservationCreateReqDto reservationRequest) {

        Reservation newReservation = reservationRepository.create(reservationRequest);

        Map<String, Long> response = Map.of("id", newReservation.getId());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}
