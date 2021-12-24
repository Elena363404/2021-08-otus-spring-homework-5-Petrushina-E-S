package ru.otus.elena363404.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.elena363404.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

  private final NamedParameterJdbcOperations jdbc;

  public AuthorDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
  {
    this.jdbc = jdbcOperations;
  }

  private static class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
      long id = resultSet.getLong("id");
      String name = resultSet.getString("name");
      return new Author(id, name);
    }
  }

  @Override
  public void createAuthor(Author author) {
    jdbc.update("insert into author(id, name) values (:id, :name)", Map.of("id", author.getId(), "name", author.getName()));
  }

  @Override
  public Author getAuthorById(long id) {
    try {
      return jdbc.queryForObject("select * from author where id = :id", Map.of("id", id), new AuthorMapper());
    } catch (EmptyResultDataAccessException err) {
      return null;
    }

  }

  @Override
  public void updateAuthor(Author author) {
    jdbc.update("update author set name = :name where id = :id", Map.of("id", author.getId(), "name", author.getName()));
  }

  @Override
  public void deleteAuthorById(long id) {
    jdbc.update("delete from author where id = :id", Map.of("id", id));
  }

  @Override
  public List<Author> getAllAuthor() {
    return jdbc.query("select * from author", new AuthorMapper());
  }

  @Override
  public long getNextAuthorId() {
    return jdbc.getJdbcOperations().queryForObject("select coalesce(max(id)+1, 1) from author", Integer.class);
  }
}
