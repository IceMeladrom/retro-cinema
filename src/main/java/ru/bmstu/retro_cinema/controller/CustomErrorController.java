package ru.bmstu.retro_cinema.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/custom-404-page")
    public String error404() {
        // Возвращаем страницу для ошибки 404
        return "custom-404-page"; // Здесь указываем путь на кастомную страницу 404
    }

    @RequestMapping("/error")
    public String handleError() {
        // Возвращаем страницу для ошибки 404
        return "redirect:/custom-404-page"; // Здесь указываем путь на кастомную страницу 404
    }

    public String getErrorPath() {
        return "/error";
    }
}

