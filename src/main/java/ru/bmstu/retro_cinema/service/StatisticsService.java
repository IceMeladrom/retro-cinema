package ru.bmstu.retro_cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.repository.FilmRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final FilmRepository filmRepository;

    public List<Object[]> get3UnpopularFilms(){
        return filmRepository.findTheMostUnpopularFilms();
    }

    public List<Object[]> get3PopularFilms(){
        return filmRepository.findTheMostPopularFilms();
    }
}
