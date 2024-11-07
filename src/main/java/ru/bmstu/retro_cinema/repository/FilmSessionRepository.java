package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bmstu.retro_cinema.entity.FilmSession;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmSessionRepository extends JpaRepository<FilmSession, Long> {
    Optional<FilmSession> findById(Long id);

    List<FilmSession> findByFilmIdAndCinemaId(Long filmId, Long cinemaId);

    List<FilmSession> findByCinemaId(Long cinemaId);

    List<FilmSession> findByFilmId(Long filmId);

    List<FilmSession> findByStartOfSession(ZonedDateTime startOfSession);

    Optional<FilmSession> findByFilmIdAndCinemaIdAndStartOfSession(Long filmId, Long cinemaId, ZonedDateTime startOfSession);

    Boolean existsByFilmIdAndCinemaId(Long filmId, Long cinemaId);
}
