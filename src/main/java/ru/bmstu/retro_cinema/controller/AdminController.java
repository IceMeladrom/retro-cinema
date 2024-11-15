package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bmstu.retro_cinema.service.CinemaService;
import ru.bmstu.retro_cinema.service.FilmService;
import ru.bmstu.retro_cinema.service.FilmSessionService;
import ru.bmstu.retro_cinema.service.StatisticsService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final FilmService filmService;
    private final CinemaService cinemaService;
    private final FilmSessionService filmSessionService;
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

//    @PostMapping("/add/film")
//    public String addFilm(@ModelAttribute FilmDto filmDto, Model model) {
//        Film film = filmService.add(FilmDto.fromDto(filmDto));
//        model.addAttribute("message", "Фильм добавлен успешно!");
//        return "redirect:/admin/dashboard";
//    }

//    @PostMapping("/add/cinema")
//    public String addCinema(@ModelAttribute CinemaDto cinemaDto, Model model) {
//        Cinema cinema = cinemaService.add(CinemaDto.fromDto(cinemaDto));
//        model.addAttribute("message", "Кинотеатр добавлен успешно!");
//        return "redirect:/admin/dashboard";
//    }

    @PostMapping("/link")
    public String linkFilmToCinema(@RequestParam Long filmId, @RequestParam Long cinemaId, RedirectAttributes redirectAttributes) {
        ResponseEntity<String> response = filmSessionService.createSession(filmId, cinemaId);

        if (response.getStatusCode().isSameCodeAs(HttpStatus.CONFLICT)) {
            redirectAttributes.addFlashAttribute("message", "Данный фильм уже есть в этом кинотеатре!");
            return "redirect:/admin/dashboard"; // Перенаправление на ту же страницу с сообщением
        }

        redirectAttributes.addFlashAttribute("message", "Фильм успешно добавлен в кинотеатр!");
        return "redirect:/admin/dashboard";
    }
}
