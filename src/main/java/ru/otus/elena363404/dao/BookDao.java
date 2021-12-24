package ru.otus.elena363404.dao;

import ru.otus.elena363404.domain.Book;

import java.util.List;

public interface BookDao {

  void createBook(Book book);

  Book getBookById(long id);

  void updateBook(Book book);

  void deleteBookById(long id);

  List<Book> getAllBookByAuthorId(long id);

  List<Book> getAllBook();

  long getNextBookId();

}
