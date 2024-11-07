package ru.bmstu.retro_cinema.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "film_session")
@Data
public class FilmSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id", referencedColumnName = "id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cinema_id", referencedColumnName = "id", nullable = false)
    private Cinema cinema;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime startOfSession;

    @OneToMany(mappedBy = "filmSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ticket> tickets;
}
