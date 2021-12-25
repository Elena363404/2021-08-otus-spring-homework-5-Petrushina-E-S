package ru.otus.elena363404.service;

public interface IdentifierGenerator {

  long generateNextBookId();

  long generateNextAuthorId();

  long generateNextGenreId();
}
