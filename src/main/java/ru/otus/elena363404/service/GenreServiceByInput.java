package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceByInput implements GenreService {

  private final IOService ioService;
  private final GenreDao genreDao;

  @Override
  public Genre createGenre() {
    ioService.out("Input name for the genre: \n");
    String nameGenre = ioService.readString();
    long idGenre = genreDao.getNextGenreId();
    return new Genre(idGenre, nameGenre);
  }

  @Override
  public Genre updateGenre() {
    ioService.out("Input id of the genre for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the genre: \n");
    String name = ioService.readString();
    return new Genre(id, name);
  }

  @Override
  public long deleteGenre() {
    ioService.out("Input id of the genre for delete: \n");
    long id = ioService.getInputId();
    return id;
  }
}
