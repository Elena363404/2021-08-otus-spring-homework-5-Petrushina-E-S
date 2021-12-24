package ru.otus.elena363404.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.elena363404.domain.Book;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import(BookDaoJdbc.class)
@Sql("/book.sql")
class BookDaoJdbcTest {

  private static final int EXISTING_BOOK_ID = 10;
  private static final String EXISTING_BOOK_NAME = "BookForTest";
  private static final int EXISTING_AUTHOR_ID = 1;
  private static final int EXISTING_GENRE_ID = 2;
  private static final int BOOK_ID_FOR_DELETE = 2;

  @Autowired
  BookDaoJdbc bookDao;

  @DisplayName("Add book in the DB")
  @Test
  void shouldInsertBook() {
    Book expectedBook= new Book(7, "BookForTest", 2, 1);
    bookDao.createBook(expectedBook);
    Book actualBook = bookDao.getBookById(expectedBook.getId());
    assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
  }

  @DisplayName("Return book by ID")
  @Test
  void shouldReturnExpectedBookById() {
    Book expectedBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, EXISTING_AUTHOR_ID, EXISTING_GENRE_ID);
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

    bookList.add(new Book(1, "Doughter of Capitan", 2, 3));
    bookList.add(new Book(2, "Apocalypse", 3, 1));
    bookList.add(new Book(3, "Revolution-1", 4, 2));
    bookList.add(new Book(4, "Revolution-2", 4, 2));
    bookList.add(new Book(5, "It", 1, 4));

    return bookList;
  }

}