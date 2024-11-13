package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bmstu.retro_cinema.repository.CinemaRepository;

@Controller
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaRepository cinemaRepository;

    @GetMapping
    public String getCinemaPage(){
        return "cinema";
    }

    @PostMapping("/create")
    public String createCinema(){

    }
}
