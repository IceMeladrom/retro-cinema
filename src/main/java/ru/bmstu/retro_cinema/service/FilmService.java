package ru.bmstu.retro_cinema.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.bmstu.retro_cinema.dto.CinemaDto;
import ru.bmstu.retro_cinema.dto.FilmDto;
import ru.bmstu.retro_cinema.entity.Cinema;
import ru.bmstu.retro_cinema.entity.Film;
import ru.bmstu.retro_cinema.repository.FilmRepository;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FilmService {
    private FilmRepository filmRepository;

    public int getNumberOfFilms() {
        return filmRepository.findAll().size();
    }

    public FilmDto create(FilmDto filmDto, MultipartFile poster) throws IOException, URISyntaxException {
        // Указываем путь для сохранения файлов
        Path uploadDir = Path.of("uploads/img/");

        // Создаем папку, если она не существует
        File directory = new File(uploadDir.toString());
        if (!directory.exists()) {
            directory.mkdirs();  // Создаем директорию
        }

        String fileName = poster.getOriginalFilename();
        Path filePath = uploadDir.resolve(fileName);
        Files.copy(poster.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Film film = FilmDto.fromDto(filmDto);
        film.setPathToPoster(filePath.toString());
        FilmDto savedFilmDto = FilmDto.toDto(filmRepository.save(film));
        return savedFilmDto;
    }

    public List<FilmDto> readAll() {
        List<Film> films = filmRepository.findAll();
        List<FilmDto> filmDtos = new ArrayList<>();
        for (Film film : films)
            filmDtos.add(FilmDto.toDto(film));
        return filmDtos;
    }

    public FilmDto read(Long filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow(EntityNotFoundException::new);
        return FilmDto.toDto(film);
    }

    public FilmDto update(Long filmId, FilmDto updatedFilmDto) {
        Film film = filmRepository.findById(filmId).orElseThrow(EntityNotFoundException::new);
        film.setTitle(updatedFilmDto.getTitle());
        film.setDescription(updatedFilmDto.getDescription());
        film.setReleaseDate(updatedFilmDto.getReleaseDate());
        film.setDuration(updatedFilmDto.getDuration());
        Film updatedFilm = filmRepository.save(film);
        return FilmDto.toDto(updatedFilm);
    }
}
