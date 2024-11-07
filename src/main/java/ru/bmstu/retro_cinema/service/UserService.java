package ru.bmstu.retro_cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bmstu.retro_cinema.enums.Role;
import ru.bmstu.retro_cinema.entity.User;
import ru.bmstu.retro_cinema.repository.UserRepository;

import java.util.Collections;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User create(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
