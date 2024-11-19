package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.retro_cinema.service.CinemaService;
import ru.bmstu.retro_cinema.service.FilmService;
import ru.bmstu.retro_cinema.service.StatisticsService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final StatisticsService statisticsService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("films", filmService.readAll());
        model.addAttribute("cinemas", cinemaService.readAll());
        model.addAttribute("selectedFilmId", null);
        model.addAttribute("selectedCinemaId", null);
        model.addAttribute("popularFilms", statisticsService.get3PopularFilms());
        model.addAttribute("unpopularFilms", statisticsService.get3UnpopularFilms());

        return "dashboard";
    }
}
