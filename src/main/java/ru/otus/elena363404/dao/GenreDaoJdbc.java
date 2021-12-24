package ru.otus.elena363404.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.elena363404.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

  private final NamedParameterJdbcOperations jdbc;

  public GenreDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
  {
    this.jdbc = jdbcOperations;
  }

  private static class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Genre(id, name);
    }
  }

  @Override
  public void createGenre(Genre genre) {
    jdbc.update("insert into genre(id, name) values (:id, :name)", Map.of("id", genre.getId(), "name", genre.getName()));
  }

  @Override
  public Genre getGenreById(long id) {
    try {
      return jdbc.queryForObject("select * from genre where id = :id", Map.of("id", id), new GenreDaoJdbc.GenreMapper());
    } catch (EmptyResultDataAccessException err) {
      return null;
    }

  }

  @Override
  public void updateGenre(Genre genre) {
    jdbc.update("update genre set name = :name where id = :id", Map.of("id", genre.getId(), "name", genre.getName()));
  }

  @Override
  public void deleteGenreById(long id) {
    jdbc.update("delete from genre where id = :id", Map.of("id", id));
  }

  @Override
  public List<Genre> getAllGenre() {
    return jdbc.query("select * from genre", new GenreDaoJdbc.GenreMapper());
  }

  @Override
  public long getNextGenreId() {
    return jdbc.getJdbcOperations().queryForObject("select coalesce(max(id)+1, 1) from genre", Integer.class);
  }
}
