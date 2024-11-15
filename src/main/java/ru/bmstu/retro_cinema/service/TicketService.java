package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.retro_cinema.dto.TicketDto;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.entity.Seat;
import ru.bmstu.retro_cinema.entity.Ticket;
import ru.bmstu.retro_cinema.repository.FilmSessionRepository;
import ru.bmstu.retro_cinema.repository.SeatRepository;
import ru.bmstu.retro_cinema.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final FilmSessionRepository filmSessionRepository;
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public FilmSession getFilmSession(Long filmSessionId) {
        return filmSessionRepository.findById(filmSessionId).orElseThrow(EntityNotFoundException::new);
    }

    public List<TicketDto> readAll() {
        List<TicketDto> tickets = new ArrayList<>();
        for (Ticket ticket : ticketRepository.findAll())
            tickets.add(TicketDto.toDto(ticket));
        return tickets;
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
                seatRepository.save(seat);

            }
        }
        return tickets;
    }
}
