package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.FilmSession;

import java.time.LocalDateTime;

@Data
public class FilmSessionDto {
    private Long cinemaId;
    private Integer hallNumber;
    private Integer price;
    private Long filmId;
    private LocalDateTime startOfSession;

//    public static FilmSession fromDto(FilmSessionDto filmSessionDto) {
//        FilmSession filmSession = new FilmSession();
//
//    }

    public static FilmSessionDto toDto(FilmSession filmSession) {
        FilmSessionDto filmSessionDto = new FilmSessionDto();
        filmSessionDto.setCinemaId(filmSessionDto.getCinemaId());
        filmSessionDto.setHallNumber(filmSessionDto.getHallNumber());
        filmSessionDto.setFilmId(filmSessionDto.getFilmId());
        filmSessionDto.setStartOfSession(filmSession.getStartOfSession());
        filmSessionDto.setPrice(filmSessionDto.getPrice());
        return filmSessionDto;
    }
}
