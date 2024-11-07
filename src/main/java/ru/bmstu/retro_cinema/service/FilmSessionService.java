package ru.bmstu.retro_cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.repository.FilmSessionRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmSessionService {
    private final FilmSessionRepository filmSessionRepository;
    private final FilmService filmService;
    private final CinemaService cinemaService;

    public FilmSession add(FilmSession filmSession) {
        return filmSessionRepository.save(filmSession);
    }

    public List<FilmSession> getAllSessions() {
        return filmSessionRepository.findAll();
    }

    public List<FilmSession> getSessionsByFilm(Long filmId) {
        return filmSessionRepository.findByFilmId(filmId);
    }

    public List<FilmSession> getSessionsByCinema(Long cinemaId) {
        return filmSessionRepository.findByCinemaId(cinemaId);
    }

    public List<FilmSession> getSessionsByFilmAndCinema(Long filmId, Long cinemaId) {
        return filmSessionRepository.findByFilmIdAndCinemaId(filmId, cinemaId);
    }

    public ResponseEntity<String> createSession(Long filmId, Long cinemaId) {
        if (filmSessionRepository.existsByFilmIdAndCinemaId(filmId, cinemaId))
            return new ResponseEntity<>("The film session is already planned!", HttpStatus.CONFLICT);

        Film film = filmService.get(filmId);
        Cinema cinema = cinemaService.get(cinemaId);

        FilmSession filmSession = new FilmSession();
        filmSession.setFilm(film);
        filmSession.setCinema(cinema);
        filmSession.setStartOfSession(ZonedDateTime.now());

        filmSessionRepository.save(filmSession);

        return new ResponseEntity<>("The film session was successfully created.", HttpStatus.CREATED);
    }
}
