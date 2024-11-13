package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hall")
@Data
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "number_of_rows", nullable = false)
    private Integer numberOfRows;
    @Column(name = "number_of_seats_per_row", nullable = false)
    private Integer numberOfSeatsPerRow;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Integer getNumberOfSeats() {
        return numberOfRows * numberOfSeatsPerRow;
    }
}
