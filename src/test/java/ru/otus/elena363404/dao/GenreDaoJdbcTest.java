package ru.otus.elena363404.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.elena363404.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@JdbcTest
@Import(GenreDaoJdbc.class)
@Sql("/genre.sql")
class GenreDaoJdbcTest {
  private static final int EXISTING_GENRE_ID = 5;
  private static final String EXISTING_GENRE_NAME = "Thriller";
  private static final int GENRE_ID_FOR_DELETE = 2;

  @Autowired
  private GenreDaoJdbc genreDao;

  @DisplayName("Add genre in the DB")
  @Test
  void shouldInsertGenre() {
    Genre expectedGenre = new Genre(5, "Thriller");
    genreDao.createGenre(expectedGenre);
    Genre actualGenre = genreDao.getGenreById(expectedGenre.getId());
    assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
  }

  @DisplayName("Return genre by ID")
  @Test
  void shouldReturnExpectedGenreById() {
    Genre expectedGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME);
    genreDao.createGenre(expectedGenre);
    Genre actualGenre = genreDao.getGenreById(expectedGenre.getId());
    assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
  }

  @DisplayName("Delete genre by ID")
  @Test
  void shouldCorrectDeleteGenreById() {
    assertNotNull(genreDao.getGenreById(GENRE_ID_FOR_DELETE));
    genreDao.deleteGenreById(GENRE_ID_FOR_DELETE);
    assertNull(genreDao.getGenreById(GENRE_ID_FOR_DELETE));
  }

  @DisplayName("Return list of the genres")
  @Test
  void shouldReturnExpectedGenresList() {
    List<Genre> genreList = getAllGenre();

    List<Genre> actualGenreList = genreDao.getAllGenre();
    assertThat(actualGenreList).usingFieldByFieldElementComparator().
      containsExactlyInAnyOrder(genreList.get(0), genreList.get(1), genreList.get(2), genreList.get(3));
  }

  List<Genre> getAllGenre() {
    List<Genre> genreList = new ArrayList<>();

    genreList.add(new Genre(1, "Fantastic"));
    genreList.add(new Genre(2, "Political"));
    genreList.add(new Genre(3, "Novel"));
    genreList.add(new Genre(4, "Horror"));
    return genreList;
  }
}