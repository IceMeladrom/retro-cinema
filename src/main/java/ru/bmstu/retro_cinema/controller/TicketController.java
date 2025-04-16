package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.service.FilmSessionService;
import ru.bmstu.retro_cinema.service.TicketService;

import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    private final FilmSessionService filmSessionService;

    @GetMapping("/orders")
    public String getTicketInfoPage(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        model.addAttribute("refunds", ticketService.getAllRefunds());
        return "ticket-info";
    }

    @GetMapping("/buy/{filmSessionId}")
    public String getBuyTicketPage(@PathVariable Long filmSessionId, Model model) {
        FilmSession filmSession = ticketService.getFilmSession(filmSessionId);
        model.addAttribute("film", filmSession.getFilm());
        model.addAttribute("startOfSession", filmSession.getStartOfSession());
        model.addAttribute("cinema", filmSession.getCinema());
        model.addAttribute("hall", filmSession.getHall());
        model.addAttribute("rows", filmSession.getHall().getNumberOfRows());
        model.addAttribute("seats", filmSession.getHall().getNumberOfSeatsPerRow());
        model.addAttribute("filmSessionId", filmSessionId);
        model.addAttribute("price", filmSession.getPrice());

        boolean[][] occupiedSeats = filmSessionService.getOccupiedSeats(filmSessionId);
        model.addAttribute("occupiedSeats", occupiedSeats);
        return "buy-ticket";
    }

    @PostMapping("/buy/{filmSessionId}")
    public String buyTicket(@PathVariable Long filmSessionId, @RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
        try {
            redirectAttributes.addFlashAttribute("lastTickets", ticketService.create(filmSessionId, params));
            return "redirect:/ticket/orders";
        } catch (BadRequestException e) {
            redirectAttributes.addFlashAttribute("error", "Место уже куплено");
            return "redirect:/buy-ticket";
        }
    }


    @GetMapping("/return/{ticketId}")
    public String refundTicket(@PathVariable UUID ticketId, RedirectAttributes redirectAttributes) {
        if (ticketService.refundTicket(ticketId).getStatusCode().isSameCodeAs(HttpStatus.OK))
            return "redirect:/ticket/orders";
        else {
            redirectAttributes.addAttribute("error", "Сделать возврат билета не получилось");
            return "redirect:/ticket/orders";
        }
    }
}
