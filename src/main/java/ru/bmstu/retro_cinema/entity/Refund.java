package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "refund")
@Data
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_session_id", referencedColumnName = "id", nullable = false)
    private FilmSession filmSession;

    @Column
    private Integer rowNumber;

    @Column
    private Integer seatNumber;
}
