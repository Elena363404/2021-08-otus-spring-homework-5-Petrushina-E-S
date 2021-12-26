package ru.otus.elena363404.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Book {
  private long id;
  private final String name;
  private final Author author;
  private final Genre genre;

  public Book(String name, Author author, Genre genre) {
    this.name = name;
    this.author = author;
    this.genre = genre;
  }

  public Book(long id, String name, Author author, Genre genre) {
    this(name, author, genre);
    this.id = id;
  }

}

