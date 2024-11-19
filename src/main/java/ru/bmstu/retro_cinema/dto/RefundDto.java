package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.Refund;
import ru.bmstu.retro_cinema.entity.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RefundDto {
    private UUID id;
    private String cinemaName;
    private String cinemaAddress;
    private String filmTitle;
    private LocalDateTime startOfSession;
    private int rowNumber;
    private int seatNumber;

    public static RefundDto toDto(Refund refund) {
        RefundDto refundDto = new RefundDto();
        refundDto.setId(refund.getId());
        refundDto.setCinemaName(refund.getFilmSession().getCinema().getName());
        refundDto.setCinemaAddress(refund.getFilmSession().getCinema().getAddress());
        refundDto.setFilmTitle(refund.getFilmSession().getFilm().getTitle());
        refundDto.setStartOfSession(refund.getFilmSession().getStartOfSession());
        refundDto.setRowNumber(refund.getRowNumber());
        refundDto.setSeatNumber(refund.getSeatNumber());
        return refundDto;
    }
}
