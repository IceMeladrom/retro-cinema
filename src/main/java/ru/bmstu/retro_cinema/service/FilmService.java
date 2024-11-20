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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
    private static final String uploadDirectory = System.getProperty("user.dir") + "/uploads";

    public int getNumberOfFilms() {
        return filmRepository.findAll().size();
    }

    public FilmDto create(FilmDto filmDto, MultipartFile poster) throws IOException, URISyntaxException {
        String fileName = poster.getOriginalFilename();
        String filePath = Paths.get(uploadDirectory, fileName).toString();
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
        stream.write(poster.getBytes());
        stream.close();

        Film film = FilmDto.fromDto(filmDto);
        film.setPathToPoster("/uploads/" + fileName);

        return FilmDto.toDto(filmRepository.save(film));
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
