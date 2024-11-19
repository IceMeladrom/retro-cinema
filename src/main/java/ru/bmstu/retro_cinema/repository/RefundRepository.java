package ru.bmstu.retro_cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bmstu.retro_cinema.entity.Refund;

import java.util.UUID;

public interface RefundRepository extends JpaRepository<Refund, UUID> {
}
