package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer row;

    @Column(nullable = false)
    private Integer seat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_session_id", referencedColumnName = "id", nullable = false)
    private FilmSession filmSession;
}
