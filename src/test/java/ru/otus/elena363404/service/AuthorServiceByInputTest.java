package ru.otus.elena363404.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.elena363404.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test AuthorServiceByInput")
@SpringBootTest(properties = {
  InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
  ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class AuthorServiceByInputTest {

  @MockBean
  private IOService ioService;

  @Autowired
  private AuthorService authorService;

  @MockBean
  Shell shell;

  @Test
  @DisplayName("Check notification on create author by input")
  void createAuthorTest() {

    Author expectedAuthor = new Author(1, "Mikhail Lermontov");
    when(ioService.readString()).thenReturn("Mikhail Lermontov");
    Author author = authorService.createAuthor();
    verify(ioService, times(1)).out("Input name for the author: \n");

    assertEquals(expectedAuthor, author);
  }


  @Test
  @DisplayName("Check notification on update author by input")
  void updateAuthorTest() {
    Author expectedAuthor = new Author(1, "Mikhail Lermontov");
    when(ioService.readString()).thenReturn("Mikhail Lermontov");
    when(ioService.getInputId()).thenReturn(1L);
    Author author = authorService.updateAuthor();
    verify(ioService, times(1)).out("Input id of the author for update: \n");
    verify(ioService, times(1)).out("Input a new name for the author: \n");
    assertEquals(expectedAuthor, author);
  }

  @Test
  @DisplayName("Check notification on delete author")
  void deleteAuthorTest() {
    long id = authorService.deleteAuthor();
    verify(ioService, times(1)).out("Input id of the author for delete: \n");
    when(ioService.getInputId()).thenReturn(1L);
  }
}