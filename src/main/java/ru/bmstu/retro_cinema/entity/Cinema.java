package ru.bmstu.retro_cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Lazy;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cinema")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "cinema", orphanRemoval = true)
    private Set<Hall> halls;
//
//    @ManyToMany
//    @JoinTable(
//            name = "cinema_film",
//            joinColumns = @JoinColumn(name = "cinema_id"),
//            inverseJoinColumns = @JoinColumn(name = "film_id")
//    )
//    private List<Film> films;
}
