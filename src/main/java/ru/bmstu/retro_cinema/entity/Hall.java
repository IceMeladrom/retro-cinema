package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hall")
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private int hallNumber;

    @Column(name = "number_of_rows", nullable = false)
    private Integer numberOfRows;

    @Column(name = "number_of_seats_per_row", nullable = false)
    private Integer numberOfSeatsPerRow;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToMany(mappedBy = "hall", orphanRemoval = true)
    private List<FilmSession> filmSessions;

    public Integer getNumberOfSeats() {
        return numberOfRows * numberOfSeatsPerRow;
    }
}
