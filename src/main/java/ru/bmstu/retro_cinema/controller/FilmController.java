package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.service.FilmService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/film")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @GetMapping
    public String getFilmsPage(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("films", filmService.readAll());
        model.addAttribute("editedFilm", new Film());
        return "film";
    }

    @PostMapping
    public String createFilm(@ModelAttribute FilmDto filmDto, @RequestParam("poster") MultipartFile poster) throws IOException, URISyntaxException {
        filmService.create(filmDto, poster);
        return "redirect:/film";
    }

    @GetMapping("/{filmId}")
    public String getFilmPage(@PathVariable Long filmId, Model model) {
        model.addAttribute("isEdit", true);
        FilmDto filmDto = filmService.read(filmId);
        model.addAttribute("editedFilm", filmDto);
        return "film";
    }

    @PostMapping("/{filmId}")
    public String updateFilm(@PathVariable Long filmId, FilmDto updatedFilmDto, Model model) {
        filmService.update(filmId, updatedFilmDto);
        return "redirect:/film";
    }
}
