package ru.otus.elena363404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.elena363404.dao.AuthorDao;
import ru.otus.elena363404.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceByInput implements AuthorService{

  private final IOService ioService;
  private final AuthorDao authorDao;

  @Override
  public Author createAuthor() {
    ioService.out("Input name for the author: \n");
    String nameAuthor = ioService.readString();
    long idAuthor = authorDao.getNextAuthorId();
    return new Author(idAuthor, nameAuthor);
  }

  @Override
  public Author updateAuthor() {
    ioService.out("Input id of the author for update: \n");
    long id = ioService.getInputId();
    ioService.out("Input a new name for the author: \n");
    String name = ioService.readString();

    return new Author(id, name);
  }

  @Override
  public long deleteAuthor() {
    ioService.out("Input id of the author for delete: \n");
    long id = ioService.getInputId();;
    return id;
  }
}
