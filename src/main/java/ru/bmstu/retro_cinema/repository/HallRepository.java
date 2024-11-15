package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bmstu.retro_cinema.entity.Hall;

import java.util.Optional;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
    Optional<Hall> findById(Long id);

    Optional<Hall> findByCinemaIdAndHallNumber(Long CinemaId, Integer HallNumber);
}
