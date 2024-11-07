package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.repository.CinemaRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CinemaService {
    private CinemaRepository cinemaRepository;

    public Cinema add(Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    public List<Cinema> getCinemas() {
        return cinemaRepository.findAll();
    }

    public Cinema get(Long id) {
        return cinemaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
