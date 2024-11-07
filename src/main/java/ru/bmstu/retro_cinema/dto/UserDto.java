package ru.bmstu.retro_cinema.dto;

import lombok.Data;
import ru.bmstu.retro_cinema.entity.User;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;

    public static User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
