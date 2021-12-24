package ru.otus.elena363404.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.elena363404.dao.AuthorDao;
import ru.otus.elena363404.dao.BookDao;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Author;
import ru.otus.elena363404.domain.Book;
import ru.otus.elena363404.domain.Genre;
import ru.otus.elena363404.service.*;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {

  private final BookDao bookDao;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;
  private final IOService ioService;
  private final BookService bookService;
  private final AuthorService authorService;
  private final GenreService genreService;

  @ShellMethod(value = "all-book", key = {"all books", "all book", "books"})
  public void getAllBook() {
    ioService.out("All books: \n" + bookDao.getAllBook().stream().map(Book::toString).collect(Collectors.joining("\n")));
  }

  @ShellMethod(value = "get-book", key = {"get book"})
  public void getBookById() {
    ioService.out("Input id of the book: \n");
    long id = ioService.getInputId();
    ioService.out(bookDao.getBookById(id) == null ? "Book with input ID not found!" : "\n" + bookDao.getBookById(id));
  }

  @ShellMethod(value = "add-book", key = {"add book", "insert book"})
  public String createBook() {
    Book book = bookService.createBook();
    bookDao.createBook(book);
    return "You have successfully added the book";
  }

  @ShellMethod(value = "update-book", key = {"update book", "upd book"})
  public String updateBook() {
    Book book = bookService.updateBook();
    bookDao.updateBook(book);
    return "You have successfully updated the book";
  }

  @ShellMethod(value = "delete-book", key = {"delete book", "del book"})
  public String deleteBook() {
    long id = bookService.deleteBook();
    bookDao.deleteBookById(id);
    return "You have successfully deleted the book";
  }

  @ShellMethod(value = "all-author", key = {"all author", "authors"})
  public void getAllAuthor() {
    ioService.out("All authors: \n" + authorDao.getAllAuthor().stream().map(Author::toString).collect(Collectors.joining("\n")));
  }

  @ShellMethod(value = "get-author", key = {"get author"})
  public void getAuthorById() {
    ioService.out("Input id of the author: \n");
    long id = ioService.getInputId();

    ioService.out(authorDao.getAuthorById(id) == null ? "Author with input ID not found!" : "\n" + authorDao.getAuthorById(id));
  }

  @ShellMethod(value = "add-author", key = {"add author", "insert author"})
  public String createAuthor() {
    Author author = authorService.createAuthor();
    authorDao.createAuthor(author);
    return "You have successfully added the author";
  }

  @ShellMethod(value = "update-author", key = {"update author", "upd author"})
  public String updateAuthor() {
    Author author = authorService.updateAuthor();
    authorDao.updateAuthor(author);
    return "You have successfully updated the author";
  }

  @ShellMethod(value = "delete-author", key = {"delete author", "del author"})
  public String deleteAuthor() {
    long id = authorService.deleteAuthor();
    authorDao.deleteAuthorById(id);
    return "You have successfully deleted the author";
  }

  @ShellMethod(value = "all-genre", key = {"all genre", "genres"})
  public void getAllGenre() {
    ioService.out("All genres: \n" + genreDao.getAllGenre().stream().map(Genre::toString).collect(Collectors.joining("\n")));
  }

  @ShellMethod(value = "get-genre", key = {"get genre"})
  public void getGenreById() {
    ioService.out("Input id of the genre: \n");
    long id = ioService.getInputId();
    ioService.out(genreDao.getGenreById(id) == null ? "Genre with input ID not found!" : "\n" + genreDao.getGenreById(id));
  }

  @ShellMethod(value = "add-genre", key = {"add genre", "insert genre"})
  public String createGenre() {
    Genre genre = genreService.createGenre();
    genreDao.createGenre(genre);
    return "You have successfully added the genre";
  }

  @ShellMethod(value = "update-genre", key = {"update genre", "upd genre"})
  public String updateGenre() {
    Genre genre = genreService.updateGenre();
    genreDao.updateGenre(genre);
    return "You have successfully updated the genre";
  }

  @ShellMethod(value = "delete-genre", key = {"delete genre", "del genre"})
  public String deleteGenre() {
    long id = genreService.deleteGenre();
    genreDao.deleteGenreById(id);
    return "You have successfully deleted the genre";
  }

}
