DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS GENRE;
drop sequence if exists book_seq;
drop sequence if exists author_seq;
drop sequence if exists genre_seq;
create sequence author_seq;
ALTER SEQUENCE author_seq RESTART WITH 5;
CREATE TABLE AUTHOR(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));
create sequence genre_seq;
ALTER SEQUENCE genre_seq RESTART WITH 5;
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));
create sequence book_seq;
ALTER SEQUENCE book_seq RESTART WITH 6;
CREATE TABLE BOOK(ID BIGINT PRIMARY KEY, NAME VARCHAR(255), AUTHOR_ID BIGINT, GENRE_ID BIGINT, FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID), FOREIGN KEY (GENRE_ID) REFERENCES GENRE(ID));
