ALTER TABLE genre ALTER COLUMN id RESTART WITH 1;
insert into genre (`name`) values ('Fantastic');
insert into genre (`name`) values ('Political');
insert into genre (`name`) values ('Novel');
insert into genre (`name`) values ('Horror');