package ru.otus.elena363404.dao;

import ru.otus.elena363404.domain.Author;

import java.util.List;

public interface AuthorDao {

  void createAuthor(Author author);

  Author getAuthorById(long id);

  void updateAuthor(Author author);

  void deleteAuthorById(long id);

  List<Author> getAllAuthor();
}
