package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TicketDto {
    private UUID id;
    private String cinemaName;
    private String cinemaAddress;
    private String filmTitle;
    private LocalDateTime startOfSession;
    private int rowNumber;
    private int seatNumber;

    public static TicketDto toDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setCinemaName(ticket.getFilmSession().getCinema().getName());
        ticketDto.setCinemaAddress(ticket.getFilmSession().getCinema().getAddress());
        ticketDto.setFilmTitle(ticket.getFilmSession().getFilm().getTitle());
        ticketDto.setStartOfSession(ticket.getFilmSession().getStartOfSession());
        ticketDto.setRowNumber(ticket.getRowNumber());
        ticketDto.setSeatNumber(ticket.getSeatNumber());
        return ticketDto;
    }
}
