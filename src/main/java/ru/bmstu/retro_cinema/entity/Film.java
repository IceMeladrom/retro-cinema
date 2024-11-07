package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bmstu.retro_cinema.enums.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
//
//    @ManyToMany(mappedBy = "films")
//    private List<Cinema> cinemas;

    @Column
    private Double rating; // Рейтинг фильма

    @Column
    private String director; // Режиссер фильма

    @Column
    private Integer duration; // Продолжительность в минутах

    @Column
    private String country; // Страна производства
}
