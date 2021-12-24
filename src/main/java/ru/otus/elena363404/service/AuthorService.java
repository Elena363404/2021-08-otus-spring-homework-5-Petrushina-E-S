package ru.otus.elena363404.service;

import ru.otus.elena363404.domain.Author;


public interface AuthorService {

  Author createAuthor();

  Author updateAuthor();

  long deleteAuthor();
}
