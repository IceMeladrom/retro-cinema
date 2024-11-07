package ru.bmstu.retro_cinema.dto;

import lombok.Builder;
import lombok.Data;
import ru.bmstu.retro_cinema.entity.Cinema;

@Data
@Builder
public class CinemaDto {
    private String name;
    private String address;

    public static Cinema fromDto(CinemaDto cinemaDto) {
        return Cinema.builder()
                .name(cinemaDto.getName())
                .address(cinemaDto.getAddress())
                .build();
    }

    public static CinemaDto toDto(Cinema cinema) {
        return CinemaDto.builder()
                .name(cinema.getName())
                .address(cinema.getAddress())
                .build();
    }
}
