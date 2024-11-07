package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.retro_cinema.entity.Film;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findById(Long id);

    List<Film> findByTitle(String title);
}
