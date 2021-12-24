package ru.otus.elena363404.service;
import ru.otus.elena363404.domain.Genre;

public interface GenreService {
  Genre createGenre();

  Genre updateGenre();

  long deleteGenre();
}
