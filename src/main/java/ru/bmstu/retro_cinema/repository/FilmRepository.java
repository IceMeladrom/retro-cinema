package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bmstu.retro_cinema.entity.Film;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findById(Long id);

    List<Film> findByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT f.title AS film_title, COUNT(t.id) AS tickets_sold FROM film f " +
            "JOIN film_session fs ON f.id = fs.film_id " +
            "LEFT JOIN ticket t ON fs.id = t.film_session_id " +
            "GROUP BY f.id, f.title " +
            "ORDER BY tickets_sold DESC " +
            "LIMIT 3;")
    List<Object[]> findTheMostPopularFilms();

    @Query(nativeQuery = true, value = "SELECT f.title AS film_title, COUNT(t.id) AS tickets_sold FROM film f " +
            "JOIN film_session fs ON f.id = fs.film_id " +
            "LEFT JOIN ticket t ON fs.id = t.film_session_id " +
            "GROUP BY f.id, f.title " +
            "ORDER BY tickets_sold ASC " +
            "LIMIT 3;")
    List<Object[]> findTheMostUnpopularFilms();
}
