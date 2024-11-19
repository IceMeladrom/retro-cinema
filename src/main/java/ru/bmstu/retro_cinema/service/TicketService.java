package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.retro_cinema.dto.RefundDto;
import ru.bmstu.retro_cinema.dto.TicketDto;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.entity.Refund;
import ru.bmstu.retro_cinema.entity.Seat;
import ru.bmstu.retro_cinema.entity.Ticket;
import ru.bmstu.retro_cinema.repository.FilmSessionRepository;
import ru.bmstu.retro_cinema.repository.RefundRepository;
import ru.bmstu.retro_cinema.repository.SeatRepository;
import ru.bmstu.retro_cinema.repository.TicketRepository;

import java.sql.Ref;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final FilmSessionRepository filmSessionRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final RefundRepository refundRepository;

    public FilmSession getFilmSession(Long filmSessionId) {
        return filmSessionRepository.findById(filmSessionId).orElseThrow(EntityNotFoundException::new);
    }

    public List<TicketDto> getAllTickets() {
        List<TicketDto> tickets = new ArrayList<>();
        for (Ticket ticket : ticketRepository.findAll())
            tickets.add(TicketDto.toDto(ticket));
        return tickets;
    }

    public List<RefundDto> getAllRefunds() {
        List<RefundDto> refunds = new ArrayList<>();
        for (Refund refund : refundRepository.findAll())
            refunds.add(RefundDto.toDto(refund));
        return refunds;
    }

    @Transactional
    public List<TicketDto> create(Long filmSessionId, Map<String, String> params) throws BadRequestException {
        List<TicketDto> tickets = new ArrayList<>();
        for (String param : params.keySet()) {
            String[] splitted = param.split(";;");
            if (splitted[0].equals("seat")) {
                Ticket ticket = new Ticket();
                int rowNumber = Integer.parseInt(splitted[1]);
                int seatNumber = Integer.parseInt(splitted[2]);
                FilmSession filmSession = getFilmSession(filmSessionId);

                if (seatRepository.existsByFilmSessionIdAndRowAndSeat(filmSessionId, rowNumber, seatNumber))
                    throw new BadRequestException("Seat already has bought");

                ticket.setRowNumber(rowNumber);
                ticket.setSeatNumber(seatNumber);
                ticket.setFilmSession(filmSession);
                ticketRepository.save(ticket);
                tickets.add(TicketDto.toDto(ticket));

                Seat seat = new Seat();
                seat.setRow(rowNumber);
                seat.setSeat(seatNumber);
                seat.setFilmSession(filmSession);
                seat.setTicket(ticket);
                seatRepository.save(seat);

            }
        }
        return tickets;
    }

    public ResponseEntity<String> refundTicket(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new EntityNotFoundException("Билет с номером " + ticketId + " не найден"));
        Refund refund = new Refund();
        refund.setId(ticket.getId());
        refund.setFilmSession(ticket.getFilmSession());
        refund.setRowNumber(ticket.getRowNumber());
        refund.setSeatNumber(ticket.getSeatNumber());

        refundRepository.save(refund);
        ticketRepository.delete(ticket);

        return ResponseEntity.ok("Билет с номером " + ticketId + " успешно возвращен");
    }
}
