package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bmstu.retro_cinema.repository.FilmRepository;
import ru.bmstu.retro_cinema.service.FilmSessionService;

import java.util.List;

@Controller
@RequestMapping("/poster")
@RequiredArgsConstructor
public class PosterController {
    private final FilmSessionService filmSessionService;
    private final FilmRepository filmRepository;

    @GetMapping
    public String getSessions(Model model) {
        model.addAttribute("filmSessions", filmSessionService.getAllSessions());
        model.addAttribute("films", filmRepository.findAll());
        return "poster";
    }

    @GetMapping("/{filmId}")
    public String getSessionByFilm(@PathVariable Long filmId,
                                   @RequestParam(defaultValue = "startOfSession") String sortBy,
                                   @RequestParam(defaultValue = "asc") String direction,
                                   Model model) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);

        model.addAttribute("filmId", filmId);
        model.addAttribute("filmSessions", filmSessionService.getSessionsByFilm(filmId, sort));
        return "film-poster";
    }
}
