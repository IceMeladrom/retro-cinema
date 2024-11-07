package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.service.FilmSessionService;

import java.util.List;

@Controller
@RequestMapping("/poster")
@RequiredArgsConstructor
public class PosterController {
    private final FilmSessionService filmSessionService;

    @GetMapping
    public String getSessions(Model model) {
        model.addAttribute("filmSessions", filmSessionService.getAllSessions());
        return "poster";
    }
}
