package ru.bmstu.retro_cinema.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "film_session")
@Data
public class FilmSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "hall_id", referencedColumnName = "id", nullable = false)
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id", referencedColumnName = "id", nullable = false)
    private Film film;

    @Column(nullable = false)
    private LocalDateTime startOfSession;

    @OneToMany(mappedBy = "filmSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    @Column
    private Integer price;

    @OneToMany(mappedBy = "seat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;
}
