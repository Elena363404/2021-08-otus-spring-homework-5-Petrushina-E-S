package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Genre;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceByInput implements GenreService {

  private final IOService ioService;
  private final IdentifierGenerator idGenerator;
  private final GenreDao genreDao;

  @Override
  public void createGenre() {
    ioService.out("Input name for the genre: \n");
    String nameGenre = ioService.readString();
    long idGenre = idGenerator.generateNextGenreId();
    Genre genre = new Genre(idGenre, nameGenre);
    genreDao.createGenre(genre);
  }

  @Override
  public void updateGenre() {
    ioService.out("Input id of the genre for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the genre: \n");
    String name = ioService.readString();
    Genre genre = new Genre(id, name);
    genreDao.updateGenre(genre);
  }

  @Override
  public void deleteGenre() {
    ioService.out("Input id of the genre for delete: \n");
    long id = ioService.getInputId();
    genreDao.deleteGenreById(id);
  }

  @Override
  public void getGenreById() {
    ioService.out("Input id of the genre: \n");
    long id = ioService.getInputId();
    ioService.out(genreDao.getGenreById(id) == null ? "Genre with input ID not found!" : "\n" + genreDao.getGenreById(id));
  }

  @Override
  public void getAllGenre() {
    ioService.out("All genres: \n" + genreDao.getAllGenre().stream().map(Genre::toString).collect(Collectors.joining("\n")));
  }
}
