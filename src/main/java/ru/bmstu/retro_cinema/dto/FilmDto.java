package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.Film;

@Data
public class FilmDto {
    private String title;

    public static Film fromDto(FilmDto filmDto) {
        Film film = new Film();
        film.setTitle(filmDto.getTitle());
        return film;
    }

    public static FilmDto toDto(Film film) {
        FilmDto filmDto = new FilmDto();
        filmDto.setTitle(film.getTitle());
        return filmDto;
    }
}
