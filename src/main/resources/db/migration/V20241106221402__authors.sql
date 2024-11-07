create table if not exists book.authors
(
    id            bigserial primary key,
    name          varchar not null ,
    surname       varchar not null ,
    date_of_birth varchar
);