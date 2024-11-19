package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.dto.FilmSessionDto;
import ru.bmstu.retro_cinema.service.CinemaService;
import ru.bmstu.retro_cinema.service.FilmService;
import ru.bmstu.retro_cinema.service.FilmSessionService;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Controller
@RequestMapping("/filmsession")
@RequiredArgsConstructor
public class FilmSessionController {
    private final CinemaService cinemaService;
    private final FilmService filmService;
    private final FilmSessionService filmSessionService;

    @GetMapping
    public String getFilmSessionPage(Model model) {
        List<CinemaDto> cinemas = cinemaService.readAll();
        List<FilmDto> films = filmService.readAll();
        model.addAttribute("cinemas", cinemas);
        model.addAttribute("films", films);
        return "filmsession";
    }

    @PostMapping
    public String createFilmSession(@ModelAttribute FilmSessionDto filmSessionDto) {
        filmSessionService.createSession(filmSessionDto);
        System.out.println(filmSessionDto);
        return "redirect:/filmsession";
    }
}
