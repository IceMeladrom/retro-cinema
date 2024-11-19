package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.dto.FilmSessionDto;
import ru.bmstu.retro_cinema.entity.*;
import ru.bmstu.retro_cinema.repository.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmSessionService {
    @Autowired
    private final FilmSessionRepository filmSessionRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;
    private final FilmRepository filmRepository;
    private final SeatRepository seatRepository;
    private final FilmService filmService;
    private final CinemaService cinemaService;

    public List<FilmSession> getAllSessions() {
        return filmSessionRepository.findAll();
    }

    public ResponseEntity<String> createSession(Long filmId, Long cinemaId) {
        if (filmSessionRepository.existsByFilmIdAndCinemaId(filmId, cinemaId))
            return new ResponseEntity<>("The film session is already planned!", HttpStatus.CONFLICT);

        Film film = FilmDto.fromDto(filmService.read(filmId));
        Cinema cinema = cinemaService.read(cinemaId);

        FilmSession filmSession = new FilmSession();
        filmSession.setFilm(film);
        filmSession.setCinema(cinema);
        filmSession.setStartOfSession(LocalDateTime.now());

        filmSessionRepository.save(filmSession);

        return new ResponseEntity<>("The film session was successfully created.", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<FilmSessionDto> createSession(FilmSessionDto filmSessionDto) {
        FilmSession filmSession = new FilmSession();
        filmSession.setCinema(cinemaRepository.findById(filmSessionDto.getCinemaId()).orElseThrow(EntityNotFoundException::new));
        filmSession.setHall(hallRepository.findByCinemaIdAndHallNumber(filmSessionDto.getCinemaId(), filmSessionDto.getHallNumber()).orElseThrow(EntityNotFoundException::new));
        filmSession.setFilm(filmRepository.findById(filmSessionDto.getFilmId()).orElseThrow(EntityNotFoundException::new));
        filmSession.setStartOfSession(filmSessionDto.getStartOfSession());
        filmSession.setPrice(filmSessionDto.getPrice());
        filmSessionRepository.save(filmSession);
        return new ResponseEntity<>(filmSessionDto, HttpStatus.CREATED);
    }

    public int getFreeSeats(Long filmSessionId) {
        FilmSession filmSession = filmSessionRepository.findById(filmSessionId).orElseThrow(EntityNotFoundException::new);
        int numberOfSeats = filmSession.getHall().getNumberOfSeats();
        int numberofOccupiedSeats = getOccupiedSeats(filmSessionId).length;
        return numberOfSeats - numberofOccupiedSeats;
    }

    public boolean[][] getOccupiedSeats(Long filmSessionId) {
        int row = filmSessionRepository.findById(filmSessionId).orElseThrow(EntityNotFoundException::new).getHall().getNumberOfRows();
        int col = filmSessionRepository.findById(filmSessionId).orElseThrow(EntityNotFoundException::new).getHall().getNumberOfSeatsPerRow();
        boolean[][] occupiedSeats = new boolean[row][col];

        List<Seat> seats = seatRepository.findByFilmSessionId(filmSessionId);
        for (Seat seat : seats)
            occupiedSeats[seat.getRow() - 1][seat.getSeat() - 1] = true;

        return occupiedSeats;
    }

    public List<FilmSession> getSessionsByFilm(Long filmId, Sort sort) {
        return filmSessionRepository.findByFilmId(filmId, sort);
    }
}
