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

    @Column(nullable = false)
    private int numberOfHalls;

    @OneToMany(mappedBy = "cinema", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Hall> halls;
}
