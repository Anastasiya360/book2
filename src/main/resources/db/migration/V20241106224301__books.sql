create table if not exists book.books
(
    id            bigserial primary key,
    name          varchar not null,
    year          int
);