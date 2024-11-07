package ru.bmstu.retro_cinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.repository.CinemaRepository;
import ru.bmstu.retro_cinema.service.CinemaService;
import ru.bmstu.retro_cinema.service.FilmService;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private FilmService filmService;
    private CinemaService cinemaService;
    private CinemaRepository cinemaRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("films", filmService.getFilms());
        model.addAttribute("cinemas", cinemaService.getCinemas());
        model.addAttribute("selectedFilmId", null);
        model.addAttribute("selectedCinemaId", null);

        return "dashboard";
    }

    @PostMapping("/add/film")
    public String addFilm(@ModelAttribute FilmDto filmDto, Model model) {
        Film film = filmService.add(FilmDto.fromDto(filmDto));
        model.addAttribute("message", "Фильм добавлен успешно!");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/add/cinema")
    public String addCinema(@ModelAttribute CinemaDto cinemaDto, Model model) {
        Cinema cinema = cinemaService.add(CinemaDto.fromDto(cinemaDto));
        model.addAttribute("message", "Кинотеатр добавлен успешно!");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/link")
    public String linkFilmToCinema(@RequestParam Long filmId, @RequestParam Long cinemaId, RedirectAttributes redirectAttributes) {
        if (cinemaRepository.existsByIdAndFilms_Id(cinemaId, filmId)) {
            redirectAttributes.addFlashAttribute("message", "Данный фильм уже есть в этом кинотеатре!");
            return "redirect:/admin/dashboard"; // Перенаправление на ту же страницу с сообщением
        }
        Film film = filmService.get(filmId);
        Cinema cinema = cinemaService.get(cinemaId);

        film.getCinemas().add(cinema);
        cinema.getFilms().add(film);

        filmService.add(film);
        cinemaService.add(cinema);

        redirectAttributes.addFlashAttribute("message", "Фильм успешно добавлен в кинотеатр!");
        return "redirect:/admin/dashboard";
    }
}
