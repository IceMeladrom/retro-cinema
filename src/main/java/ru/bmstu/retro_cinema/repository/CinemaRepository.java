package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bmstu.retro_cinema.entity.Cinema;

import java.util.List;
import java.util.Optional;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    Optional<Cinema> findById(Long id);

    List<Cinema> findByName(String name);

    boolean existsByIdAndFilms_Id(Long cinemaId, Long filmId);
}
