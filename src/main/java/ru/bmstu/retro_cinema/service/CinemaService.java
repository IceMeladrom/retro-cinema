package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.repository.CinemaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService {
    private CinemaRepository cinemaRepository;

    public CinemaDto create(CinemaDto cinemaDto) {
        Cinema convertedCinema = CinemaDto.fromDto(cinemaDto);
        Cinema savedCinema = cinemaRepository.save(convertedCinema);
        return CinemaDto.toDto(savedCinema);
    }

    public List<CinemaDto> readAll() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaDto> cinemaDtos = new ArrayList<>();
        for (Cinema cinema : cinemas)
            cinemaDtos.add(CinemaDto.toDto(cinema));
        return cinemaDtos;
    }

    public Cinema read(Long id) {
        return cinemaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
