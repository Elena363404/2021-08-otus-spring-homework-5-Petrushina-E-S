package ru.otus.elena363404.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
@Sql({"/author.sql", "/genre.sql", "/book.sql"})
class BookDaoJdbcTest {

  private static final int EXISTING_BOOK_ID = 10;
  private static final String EXISTING_BOOK_NAME = "BookForTest";
  private static final int EXISTING_AUTHOR_ID = 1;
  private static final String EXISTING_AUTHOR_NAME = "Gogol";
  private static final int EXISTING_GENRE_ID = 2;
  private static final String EXISTING_GENRE_NAME = "Novel";
  private static final int BOOK_ID_FOR_DELETE = 2;

  @Autowired
  BookDaoJdbc bookDao;

  @DisplayName("Add book in the DB")
  @Test
  void shouldInsertBook() {
    Book expectedBook = new Book(6,"BookForTest", new Author(2,"Alexander Pushkin"), new Genre(3,"Novel"));
    bookDao.createBook(expectedBook);
    Book actualBook = bookDao.getBookById(expectedBook.getId());
    assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
  }

  @DisplayName("Return book by ID")
  @Test
  void shouldReturnExpectedBookById() {
    Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME), new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
    Book actualBook = bookDao.getBookById(expectedBook.getId());
    assertThat(actualBook).usingRecursiveComparison().isEqualTo(actualBook);
  }

  @DisplayName("Delete book by ID")
  @Test
  void shouldCorrectDeleteBookById() {
    assertNotNull(bookDao.getBookById(BOOK_ID_FOR_DELETE));
    bookDao.deleteBookById(BOOK_ID_FOR_DELETE);
    assertNull(bookDao.getBookById(BOOK_ID_FOR_DELETE));
  }

  @DisplayName("Return list of the books")
  @Test
  void shouldReturnExpectedBooksList() {
    List<Book> bookList = getAllBooks();

    List<Book> actualAuthorList = bookDao.getAllBook();
    assertThat(actualAuthorList).usingFieldByFieldElementComparator().
      containsExactlyInAnyOrder(bookList.get(0), bookList.get(1), bookList.get(2), bookList.get(3), bookList.get(4));
  }

  private List<Book> getAllBooks() {
    List<Book> bookList = new ArrayList<>();
    List<Author> authorList = getAllAuthor();
    List<Genre> genreList = getAllGenre();

    bookList.add(new Book(1, "Doughter of Capitan", authorList.get(1), genreList.get(2)));
    bookList.add(new Book(2, "Apocalypse", authorList.get(2), genreList.get(0)));
    bookList.add(new Book(3, "Revolution-1", authorList.get(3), genreList.get(1)));
    bookList.add(new Book(4, "Revolution-2", authorList.get(3), genreList.get(1)));
    bookList.add(new Book(5, "It", authorList.get(0), genreList.get(3)));

    return bookList;
  }

  List<Author> getAllAuthor() {
    List<Author> authorList = new ArrayList<>();

    authorList.add(new Author(1, "Stephen King"));
    authorList.add(new Author(2, "Alexander Pushkin"));
    authorList.add(new Author(3, "Isaak Newton"));
    authorList.add(new Author(4, "Vladimir Lenin"));

    return authorList;
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