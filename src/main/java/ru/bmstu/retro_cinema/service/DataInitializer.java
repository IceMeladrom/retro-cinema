package ru.bmstu.retro_cinema.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bmstu.retro_cinema.entity.*;
import ru.bmstu.retro_cinema.enums.Role;
import ru.bmstu.retro_cinema.repository.CinemaRepository;
import ru.bmstu.retro_cinema.repository.FilmRepository;
import ru.bmstu.retro_cinema.repository.FilmSessionRepository;
import ru.bmstu.retro_cinema.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (userRepository.findByUsername("admin").isEmpty()) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setEmail("admin@admin.com");
            adminUser.setRoles(Collections.singleton(Role.ADMIN));

            userRepository.save(adminUser);
        }

        if (userRepository.findByUsername("cashier").isEmpty()) {
            User cashierUser = new User();
            cashierUser.setUsername("cashier");
            cashierUser.setPassword(passwordEncoder.encode("cashier"));
            cashierUser.setEmail("cashier@cashier.com");
            cashierUser.setRoles(Collections.singleton(Role.CASHIER));

            userRepository.save(cashierUser);
        }

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
        karo10.setNumberOfHalls(2);
        Hall hall1 = new Hall();
        hall1.setHallNumber(1);
        hall1.setNumberOfRows(5);
        hall1.setNumberOfSeatsPerRow(8);
        hall1.setCinema(karo10);
        Hall hall2 = new Hall();
        hall2.setHallNumber(2);
        hall2.setNumberOfRows(7);
        hall2.setNumberOfSeatsPerRow(13);
        hall2.setCinema(karo10);
        karo10.setHalls(new ArrayList<>() {{
            add(hall1);
            add(hall2);
        }});

        Cinema karo11 = new Cinema();
        karo11.setName("Каро 11 Октябрь");
        karo11.setAddress("Москва, ул. Новый Арбат, 24");
        karo11.setNumberOfHalls(3);
        Hall hall3 = new Hall();
        hall3.setHallNumber(1);
        hall3.setNumberOfRows(2);
        hall3.setNumberOfSeatsPerRow(17);
        hall3.setCinema(karo11);
        Hall hall4 = new Hall();
        hall4.setHallNumber(2);
        hall4.setNumberOfRows(14);
        hall4.setNumberOfSeatsPerRow(8);
        hall4.setCinema(karo11);
        Hall hall5 = new Hall();
        hall5.setHallNumber(3);
        hall5.setNumberOfRows(7);
        hall5.setNumberOfSeatsPerRow(6);
        hall5.setCinema(karo11);
        karo11.setHalls(new ArrayList<>() {{
            add(hall3);
            add(hall4);
            add(hall5);
        }});

        cinemaRepository.save(karo10);
        cinemaRepository.save(karo11);

        FilmSession drive_karo10_session = new FilmSession();
        drive_karo10_session.setFilm(drive);
        drive_karo10_session.setCinema(karo10);
        drive_karo10_session.setHall(karo10.getHalls().getFirst());
        drive_karo10_session.setStartOfSession(LocalDateTime.now());

        FilmSession americanPsycho_karo10_session = new FilmSession();
        americanPsycho_karo10_session.setFilm(americanPsycho);
        americanPsycho_karo10_session.setCinema(karo10);
        americanPsycho_karo10_session.setHall(karo10.getHalls().get(1));
        americanPsycho_karo10_session.setStartOfSession(LocalDateTime.now().plusDays(1));

        FilmSession fightClub_karo11_session = new FilmSession();
        fightClub_karo11_session.setFilm(fightClub);
        fightClub_karo11_session.setCinema(karo11);
        fightClub_karo11_session.setHall(karo11.getHalls().getFirst());
        fightClub_karo11_session.setStartOfSession(LocalDateTime.now().plusDays(2));

        FilmSession americanPsycho_karo11_session = new FilmSession();
        americanPsycho_karo11_session.setFilm(americanPsycho);
        americanPsycho_karo11_session.setCinema(karo11);
        americanPsycho_karo11_session.setHall(karo11.getHalls().get(1));
        americanPsycho_karo11_session.setStartOfSession(LocalDateTime.now().plusDays(3));

        FilmSession drive_karo11_session = new FilmSession();
        drive_karo11_session.setFilm(drive);
        drive_karo11_session.setCinema(karo11);
        drive_karo11_session.setHall(karo11.getHalls().get(2));
        drive_karo11_session.setStartOfSession(LocalDateTime.now().plusDays(4));

        filmSessionRepository.save(drive_karo10_session);
        filmSessionRepository.save(drive_karo11_session);
        filmSessionRepository.save(americanPsycho_karo10_session);
        filmSessionRepository.save(americanPsycho_karo11_session);
        filmSessionRepository.save(fightClub_karo11_session);

    }
}
