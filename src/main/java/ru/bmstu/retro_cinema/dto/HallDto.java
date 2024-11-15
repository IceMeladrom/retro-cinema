package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Hall;

@Data
public class HallDto {
    private Long id;
    private String name;
    private int hallNumber;
    private int row; // Количество рядов в зале
    private int col; // Количество мест в ряду

    public static Hall fromDto(HallDto hallDto, Cinema cinema) {
        Hall hall = new Hall();
        hall.setName(hallDto.getName());
        hall.setHallNumber(hallDto.getHallNumber());
        hall.setNumberOfRows(hallDto.getRow());
        hall.setNumberOfSeatsPerRow(hallDto.getCol());
        hall.setCinema(cinema);
        return hall;
    }

    public static HallDto toDto(Hall hall) {
        HallDto hallDto = new HallDto();
        hallDto.setId(hall.getId());
        hallDto.setName(hall.getName());
        hallDto.setHallNumber(hall.getHallNumber());
        hallDto.setRow(hall.getNumberOfRows());
        hallDto.setCol(hall.getNumberOfSeatsPerRow());
        return hallDto;
    }
}