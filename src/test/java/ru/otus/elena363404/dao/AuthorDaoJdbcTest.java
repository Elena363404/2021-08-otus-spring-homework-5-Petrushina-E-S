package ru.otus.elena363404.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.elena363404.domain.Author;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@Sql("/author.sql")
class AuthorDaoJdbcTest {

  private static final int EXISTING_AUTHOR_ID = 10;
  private static final String EXISTING_AUTHOR_NAME = "Marina Tsvetaeva";
  private static final int AUTHOR_ID_FOR_DELETE = 2;

  @Autowired
  private AuthorDaoJdbc authorDao;

  @DisplayName("Add author in the DB")
  @Test
  void shouldInsertAuthor() {
    Author expectedAuthor = new Author(7, "Lermontov");
    authorDao.createAuthor(expectedAuthor);
    Author actualAuthor = authorDao.getAuthorById(expectedAuthor.getId());
    assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
  }

  @DisplayName("Return author by ID")
  @Test
  void shouldReturnExpectedAuthorById() {
    Author expectedAuthor = new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME);
    authorDao.createAuthor(expectedAuthor);
    Author actualAuthor = authorDao.getAuthorById(expectedAuthor.getId());
    assertThat(expectedAuthor).usingRecursiveComparison().isEqualTo(actualAuthor);
  }

  @DisplayName("Delete author by ID")
  @Test
  void shouldCorrectDeleteAuthorById() {
    assertNotNull(authorDao.getAuthorById(AUTHOR_ID_FOR_DELETE));
    authorDao.deleteAuthorById(AUTHOR_ID_FOR_DELETE);
    assertNull(authorDao.getAuthorById(AUTHOR_ID_FOR_DELETE));
  }

  @DisplayName("Return list of the authors")
  @Test
  void shouldReturnExpectedAuthorsList() {
    List<Author> authorList = getAllAuthor();

    List<Author> actualAuthorList = authorDao.getAllAuthor();
    assertThat(actualAuthorList).usingFieldByFieldElementComparator().
      containsExactlyInAnyOrder(authorList.get(0), authorList.get(1), authorList.get(2), authorList.get(3));
  }

  List<Author> getAllAuthor() {
    List<Author> authorList = new ArrayList<>();

    authorList.add(new Author(1, "Stephen King"));
    authorList.add(new Author(2, "Alexander Pushkin"));
    authorList.add(new Author(3, "Isaak Newton"));
    authorList.add(new Author(4, "Vladimir Lenin"));

    return authorList;
  }



}