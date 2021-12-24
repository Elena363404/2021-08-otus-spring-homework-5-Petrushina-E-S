package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.dao.AuthorDao;
import ru.otus.elena363404.dao.BookDao;
import ru.otus.elena363404.dao.GenreDao;
import ru.otus.elena363404.domain.Book;

@Service
@RequiredArgsConstructor
public class BookServiceByInput implements BookService {

  private final IOService ioService;
  private final BookDao bookDao;
  private final AuthorDao authorDao;
  private final GenreDao genreDao;

  @Override
  public Book createBook() {

    ioService.out("Input name for the book: \n");
    String nameBook = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    long author_id = ioService.getInputId();
    ioService.out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());
    long genre_id = ioService.getInputId();
    long idBook = bookDao.getNextBookId();
    return new Book(idBook, nameBook, author_id, genre_id);
  }

  @Override
  public Book updateBook() {
    ioService.out("Input id of the book for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the book: \n");
    String name = ioService.readString();
    ioService.out("Input id Author for the book from List: \n" + authorDao.getAllAuthor());
    long author_id = ioService.getInputId();
    ioService.out("Input id Genre for the book from List: \n" + genreDao.getAllGenre());
    long genre_id = ioService.getInputId();

    return new Book(id, name, author_id, genre_id);
  }

  @Override
  public long deleteBook() {
    ioService.out("Input id of the book for delete: \n");
    long id = ioService.getInputId();
    return id;
  }
}
