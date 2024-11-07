create table if not exists book.readers
(
    id            bigserial primary key,
    phone_number  varchar not null,
    name          varchar not null,
    surname       varchar not null,
    gender        boolean,
    date_of_birth varchar
);