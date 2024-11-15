package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.repository.FilmRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmService {
    private FilmRepository filmRepository;

    public FilmDto create(FilmDto filmDto) {
        Film film = FilmDto.fromDto(filmDto);
        FilmDto savedFilmDto = FilmDto.toDto(filmRepository.save(film));
        return savedFilmDto;
    }

    public List<FilmDto> readAll() {
        List<Film> films = filmRepository.findAll();
        List<FilmDto> filmDtos = new ArrayList<>();
        for (Film film : films)
            filmDtos.add(FilmDto.toDto(film));
        return filmDtos;
    }

    public FilmDto read(Long filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow(EntityNotFoundException::new);
        return FilmDto.toDto(film);
    }

    public FilmDto update(Long filmId, FilmDto updatedFilmDto) {
        Film film = filmRepository.findById(filmId).orElseThrow(EntityNotFoundException::new);
        film.setTitle(updatedFilmDto.getTitle());
        film.setDescription(updatedFilmDto.getDescription());
        film.setReleaseDate(updatedFilmDto.getReleaseDate());
        film.setDuration(updatedFilmDto.getDuration());
        Film updatedFilm = filmRepository.save(film);
        return FilmDto.toDto(updatedFilm);
    }
}
