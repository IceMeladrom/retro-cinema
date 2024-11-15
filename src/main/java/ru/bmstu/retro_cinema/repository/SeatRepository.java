package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.retro_cinema.entity.Seat;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    boolean existsByFilmSessionIdAndRowAndSeat(Long filmSessionId, Integer row, Integer seat);

    List<Seat> findByFilmSessionId(Long filmSessionId);
}
