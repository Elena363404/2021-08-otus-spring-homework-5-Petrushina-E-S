package ru.otus.elena363404.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.elena363404.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long author_id = resultSet.getLong("author_id");
            long genre_id = resultSet.getLong("genre_id");
            return new Book(id, name, author_id, genre_id);
        }
    }

    @Override
    public void createBook(Book book) {
        jdbc.update("insert into book(id, name, author_id, genre_id) values (:id, :name, :author_id, :genre_id)", Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthor_id(), "genre_id", book.getGenre_id()));
    }

    @Override
    public Book getBookById(long id) {
        try {
            return jdbc.queryForObject("select * from book where id = :id", Map.of("id", id), new BookDaoJdbc.BookMapper());
        } catch (EmptyResultDataAccessException err) {
            return null;
        }
    }

    @Override
    public void updateBook(Book book) {
        jdbc.update("update book set name = :name, author_id = :author_id, genre_id = :genre_id  where id = :id", Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthor_id(), "genre_id", book.getGenre_id()));
    }

    @Override
    public void deleteBookById(long id) {
        jdbc.update("delete from book where id = :id", Map.of("id", id));
    }

    @Override
    public List<Book> getAllBookByAuthorId(long id) {
        return jdbc.query("select * from book where author_id = :author_id", Map.of("author_id", id), new BookDaoJdbc.BookMapper());
    }

    @Override
    public List<Book> getAllBook() {
        return jdbc.query("select * from book", new BookDaoJdbc.BookMapper());
    }

    @Override
    public long getNextBookId() {
        return jdbc.getJdbcOperations().queryForObject("select coalesce(max(id)+1, 1) from book", Integer.class);
    }
}
