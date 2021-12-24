package ru.otus.elena363404.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Book {
  private final long id;
  private final String name;
  private final long author_id;
  private final long genre_id;
}

