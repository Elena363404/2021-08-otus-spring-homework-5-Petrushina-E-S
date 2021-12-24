package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Book;

public interface BookService {

  Book createBook();

  Book updateBook();

  long deleteBook();
}
