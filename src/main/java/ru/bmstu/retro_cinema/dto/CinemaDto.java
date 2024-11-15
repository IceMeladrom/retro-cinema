package ru.bmstu.retro_cinema.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Hall;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@ToString
public class CinemaDto {
    private Long id;
    private String name;
    private String address;
    private int numberOfHalls;
    private List<HallDto> halls;


    public static Cinema fromDto(CinemaDto cinemaDto) {
        Cinema cinema = Cinema.builder()
                .name(cinemaDto.getName())
                .address(cinemaDto.getAddress())
                .numberOfHalls(cinemaDto.getNumberOfHalls())
                .build();
        List<Hall> hallEntities = new ArrayList<>();
        for (HallDto hallDto : cinemaDto.getHalls())
            hallEntities.add(HallDto.fromDto(hallDto, cinema));
        cinema.setHalls(hallEntities);

        return cinema;
    }

    public static CinemaDto toDto(Cinema cinema) {
        List<HallDto> hallDtos = new ArrayList<>();
        for (Hall hall : cinema.getHalls())
            hallDtos.add(HallDto.toDto(hall));

        return CinemaDto.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .numberOfHalls(cinema.getNumberOfHalls())
                .halls(hallDtos)
                .build();
    }
}
