package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "film")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title; // Название фильма

    @Column(length = 1000)
    private String description; // Описание фильма

    @Column
    private LocalDate releaseDate; // Дата выхода

    @Column
    private Integer duration; // Продолжительность в минутах

    // Связь с кинотеатрами, где показывают этот фильм
    @ManyToMany
    @JoinTable(
            name = "film_cinema",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    private List<Cinema> cinemas;

    // Связь с сеансами, где показывают этот фильм
    @ManyToMany
    @JoinTable(
            name = "film_film-sessions",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "filmSession_id")
    )
    private List<FilmSession> filmSessions;
}
