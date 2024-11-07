package ru.bmstu.retro_cinema.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.bmstu.retro_cinema.dto.UserDto;
import ru.bmstu.retro_cinema.entity.User;
import ru.bmstu.retro_cinema.repository.UserRepository;
import ru.bmstu.retro_cinema.service.UserService;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserDto userDto, Model model) {
        Optional<User> userFromDb = userRepository.findByUsername(userDto.getUsername());
        if (userFromDb.isPresent()){
            model.addAttribute("registrationError", "User already exists!");
            return "/registration";
        }
        userService.create(UserDto.fromDto(userDto));
        return "redirect:/login";
    }
}
