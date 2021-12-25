package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.dao.AuthorDao;
import ru.otus.elena363404.dao.BookDao;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Book;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceByInput implements BookService {

  private final IOService ioService;
  private final IdentifierGenerator idGenerator;
  private final BookDao bookDao;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  @Override
  public void createBook() {

    ioService.out("Input name for the book: \n");
    String nameBook = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    long author_id = ioService.getInputId();
    ioService.out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());
    long genre_id = ioService.getInputId();
    long idBook = idGenerator.generateNextBookId();
    Book book = new Book(idBook, nameBook, authorDao.getAuthorById(author_id), genreDao.getGenreById(genre_id));

    bookDao.createBook(book);
  }

  @Override
  public void updateBook() {
    ioService.out("Input id of the book for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the book: \n");
    String name = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    long author_id = ioService.getInputId();
    ioService.out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());
    long genre_id = ioService.getInputId();
    Book book = new Book(id, name, authorDao.getAuthorById(author_id), genreDao.getGenreById(genre_id));
    bookDao.updateBook(book);
  }

  @Override
  public void deleteBook() {
    ioService.out("Input id of the book for delete: \n");
    long id = ioService.getInputId();
    bookDao.deleteBookById(id);
  }

  @Override
  public void getBookById() {
    ioService.out("Input id of the book: \n");
    long id = ioService.getInputId();
    ioService.out(bookDao.getBookById(id) == null ? "Book with input ID not found!" : "\n" + bookDao.getBookById(id));
  }

  @Override
  public void getAllBook() {
    ioService.out("All books: \n" + bookDao.getAllBook().stream().map(Book::toString).collect(Collectors.joining("\n")));
  }
}
