package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.elena363404.dao.AuthorDao;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Test BookServiceByInput")
@SpringBootTest(properties = {
  InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
  ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class BookServiceByInputTest {

  @MockBean
  private IOService ioService;

  @Autowired
  private BookService bookService;

  @Autowired
  private AuthorDao authorDao;

  @Autowired
  private GenreDao genreDao;

  @MockBean
  Shell shell;

  @Test
  @DisplayName("Check notification on create book by input")
  void createBook() {
    Book expectedBook = new Book(1, "Oblomov", 2, 4);
    when(ioService.readString()).thenReturn("Oblomov");
    when(ioService.getInputId()).thenReturn(2L).thenReturn(4L);
    Book book = bookService.createBook();
    verify(ioService, times(1)).out("Input name for the book: \n");
    verify(ioService, times(1)).out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    verify(ioService, times(1)).out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());

    assertEquals(expectedBook, book);
  }

  @Test
  @DisplayName("Check notification on update book by input")
  void updateBook() {
    Book expectedBook = new Book(1, "Oblomov", 2, 4);
    when(ioService.readString()).thenReturn("Oblomov");
    when(ioService.getInputId()).thenReturn(1L).thenReturn(2L).thenReturn(4L);
    Book book = bookService.updateBook();
    verify(ioService, times(1)).out("Input id of the book for update: \n");
    verify(ioService, times(1)).out("Input a new name for the book: \n");
    verify(ioService, times(1)).out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    verify(ioService, times(1)).out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());
    assertEquals(expectedBook, book);
  }

  @Test
  @DisplayName("Check notification on delete book by input")
  void deleteBook() {
    long id = bookService.deleteBook();
    verify(ioService, times(1)).out("Input id of the book for delete: \n");
    when(ioService.getInputId()).thenReturn(1L);
  }
}