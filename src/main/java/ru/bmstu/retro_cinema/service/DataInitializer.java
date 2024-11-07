package ru.bmstu.retro_cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.bmstu.retro_cinema.enums.Role;
import ru.bmstu.retro_cinema.entity.User;
import ru.bmstu.retro_cinema.repository.UserRepository;

import java.util.Collections;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

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
        }
    }
}
