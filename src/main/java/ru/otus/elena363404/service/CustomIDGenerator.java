package ru.otus.elena363404.service;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

@Service
public class CustomIDGenerator implements IdentifierGenerator {

  private final NamedParameterJdbcOperations jdbc;

  public CustomIDGenerator(NamedParameterJdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public long generateNextBookId() {
    return jdbc.getJdbcOperations().queryForObject("select book_seq.nextval from dual", Integer.class);
  }

  @Override
  public long generateNextAuthorId() {
    return jdbc.getJdbcOperations().queryForObject("select author_seq.nextval from dual", Integer.class);
  }

  @Override
  public long generateNextGenreId() {
    return jdbc.getJdbcOperations().queryForObject("select genre_seq.nextval from dual", Integer.class);
  }
}
