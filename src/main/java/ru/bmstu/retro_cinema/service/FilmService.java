package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.repository.FilmRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmService {
    private FilmRepository filmRepository;

    public Film add(Film film) {
        return filmRepository.save(film);
    }

    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    public Film get(Long id) {
        return filmRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
