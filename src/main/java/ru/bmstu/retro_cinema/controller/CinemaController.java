package ru.bmstu.retro_cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.service.CinemaService;

@Controller
@RequestMapping("/cinema")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @GetMapping
    public String getCinemaPage(Model model) {
        model.addAttribute("cinemas", cinemaService.readAll());
        return "cinema";
    }

    @GetMapping("/{cinemaId}")
    public ResponseEntity<CinemaDto> getCinemaPage2(@PathVariable Long cinemaId, Model model) {
        Cinema cinema = cinemaService.read(cinemaId);
        CinemaDto cinemaDto = CinemaDto.toDto(cinema);
        return new ResponseEntity<>(cinemaDto, HttpStatus.OK);
    }

    @PostMapping
    public String createCinema(@ModelAttribute CinemaDto cinemaDto, RedirectAttributes redirectAttributes) {
        CinemaDto savedCinemaDto = cinemaService.create(cinemaDto);
        redirectAttributes.addFlashAttribute("savedCinema", savedCinemaDto);
        return "redirect:/cinema";
    }
}
