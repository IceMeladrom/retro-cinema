package ru.bmstu.retro_cinema.dto;

import jakarta.persistence.Column;
import lombok.Data;
import ru.bmstu.retro_cinema.entity.Film;

import java.time.LocalDate;

@Data
public class FilmDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer duration;

    public static Film fromDto(FilmDto filmDto) {
        Film film = new Film();
        film.setTitle(filmDto.getTitle());
        film.setDescription(filmDto.getDescription());
        film.setReleaseDate(filmDto.getReleaseDate());
        film.setDuration(filmDto.getDuration());
        return film;
    }

    public static FilmDto toDto(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setId(film.getId());
        filmDto.setTitle(film.getTitle());
        filmDto.setDescription(film.getDescription());
        filmDto.setReleaseDate(film.getReleaseDate());
        filmDto.setDuration(film.getDuration());
        return filmDto;
    }
}
