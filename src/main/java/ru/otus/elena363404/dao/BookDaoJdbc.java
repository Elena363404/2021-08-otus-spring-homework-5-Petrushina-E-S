package ru.otus.elena363404.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final NamedParameterJdbcOperations jdbc;

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long author_id = resultSet.getLong("author_id");
            String author_name = resultSet.getString("author_name");
            long genre_id = resultSet.getLong("genre_id");
            String genre_name = resultSet.getString("genre_name");

            return new Book(id, name, new Author(author_id, author_name), new Genre(genre_id, genre_name));
        }
    }

    @Override
    public void createBook(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
        jdbc.update("insert into book(name, author_id, genre_id) values (:name, :author_id, :genre_id)", Map.of( "name", book.getName(), "author_id", author.getId(), "genre_id", genre.getId()));
    }

    @Override
    public Book getBookById(long id) {
        try {
            return jdbc.queryForObject(
              "select " +
              "  b.id," +
              "  b.name," +
              "  b.author_id," +
              "  b.genre_id, " +
              "  a.name as author_name," +
              "  g.name as genre_name " +
              "from " +
              "  book b " +
              "left join " +
              "  author a on b.author_id = a.id " +
              "left join" +
              "  genre g on b.genre_id = g.id " +
              "where " +
              "  b.id = :id", Map.of("id", id), new BookDaoJdbc.BookMapper());
        } catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
        jdbc.update("update book set name = :name, author_id = :author_id, genre_id = :genre_id  where id = :id", Map.of("id", book.getId(), "name", book.getName(), "author_id", author.getId(), "genre_id", genre.getId()));
    }

    @Override
    public void deleteBookById(long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    @Override
    public List<Book> getAllBookByAuthorId(long id) {
        return jdbc.query(
          "select " +
          "  b.id," +
          "  b.name," +
          "  b.author_id," +
          "  b.genre_id, " +
          "  a.name as author_name," +
          "  g.name as genre_name " +
          "from " +
          "  book b " +
          "join " +
          "  author a on b.author_id = a.id " +
          "left join" +
          "  genre g on b.genre_id = g.id " +
          "where " +
          "  b.author_id = :author_id", Map.of("author_id", id), new BookDaoJdbc.BookMapper());
    }

    @Override
    public List<Book> getAllBook() {

        return jdbc.query("select " +
          "  b.id," +
          "  b.name," +
          "  b.author_id," +
          "  b.genre_id, " +
          "  a.name as author_name," +
          "  g.name as genre_name " +
          "from " +
          "  book b " +
          "left join " +
          "  author a on b.author_id = a.id " +
          "left join" +
          "  genre g on b.genre_id = g.id", new BookDaoJdbc.BookMapper());
    }
}
