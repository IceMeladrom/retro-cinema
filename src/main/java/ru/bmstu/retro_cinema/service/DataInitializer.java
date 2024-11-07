package ru.bmstu.retro_cinema.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.entity.FilmSession;
import ru.bmstu.retro_cinema.enums.Role;
import ru.bmstu.retro_cinema.entity.User;
import ru.bmstu.retro_cinema.repository.CinemaRepository;
import ru.bmstu.retro_cinema.repository.FilmRepository;
import ru.bmstu.retro_cinema.repository.FilmSessionRepository;
import ru.bmstu.retro_cinema.repository.UserRepository;

import java.util.Collections;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;
    private final FilmSessionRepository filmSessionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Проверяем, есть ли пользователь с именем "admin"
        if (userRepository.findByUsername("admin").isEmpty()) {
            // Если нет, создаем нового пользователя с ролью ADMIN
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setEmail("admin@admin.com");
            adminUser.setRoles(Collections.singleton(Role.ADMIN));

            userRepository.save(adminUser); // Сохраняем пользователя в базе данных

            Film drive = new Film();
            drive.setTitle("Драйв");

            Film americanPsycho = new Film();
            americanPsycho.setTitle("Американский психопат");

            Film fightClub = new Film();
            fightClub.setTitle("Бойцовский клуб");

            filmRepository.save(drive);
            filmRepository.save(americanPsycho);
            filmRepository.save(fightClub);

            Cinema karo10 = new Cinema();
            karo10.setName("Каро 10 София");
            karo10.setAddress("Москва, ул. Сиреневый Бульвар, 31");

            Cinema karo11 = new Cinema();
            karo11.setName("Каро 11 Октябрь");
            karo11.setAddress("Москва, ул. Новый Арбат, 24");

            cinemaRepository.save(karo10);
            cinemaRepository.save(karo11);

            FilmSession drive_karo10_session = new FilmSession();
            drive_karo10_session.setFilm(drive);
            drive_karo10_session.setCinema(karo10);

            FilmSession americanPsycho_karo10_session = new FilmSession();
            americanPsycho_karo10_session.setFilm(americanPsycho);
            americanPsycho_karo10_session.setCinema(karo10);

            FilmSession fightClub_karo11_session = new FilmSession();
            fightClub_karo11_session.setFilm(fightClub);
            fightClub_karo11_session.setCinema(karo11);

            FilmSession americanPsycho_karo11_session = new FilmSession();
            americanPsycho_karo11_session.setFilm(americanPsycho);
            americanPsycho_karo11_session.setCinema(karo11);

            FilmSession drive_karo11_session = new FilmSession();
            drive_karo11_session.setFilm(drive);
            drive_karo11_session.setCinema(karo11);

            filmSessionRepository.save(drive_karo10_session);
            filmSessionRepository.save(drive_karo11_session);
            filmSessionRepository.save(americanPsycho_karo10_session);
            filmSessionRepository.save(americanPsycho_karo11_session);
            filmSessionRepository.save(fightClub_karo11_session);

        }
    }
}
