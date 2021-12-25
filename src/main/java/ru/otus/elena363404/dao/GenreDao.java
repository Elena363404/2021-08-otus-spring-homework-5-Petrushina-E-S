package ru.otus.elena363404.dao;

import ru.otus.elena363404.domain.Genre;

import java.util.List;

public interface GenreDao {

  void createGenre(Genre genre);

  Genre getGenreById(long id);

  void updateGenre(Genre genre);

  void deleteGenreById(long id);

  List<Genre> getAllGenre();
}
