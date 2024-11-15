package ru.bmstu.retro_cinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FilmSessionDto {
    private Long cinemaId;
    private Integer hallNumber;
    private Long filmId;
    private LocalDateTime startOfSession;

//    public static FilmSession fromDto(FilmSessionDto filmSessionDto){
//        FilmSession filmSession = new FilmSession();
//
//    }
}
