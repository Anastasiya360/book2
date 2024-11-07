create table if not exists book.employees
(
    id       bigserial primary key,
    login    varchar not null,
    password varchar not null
);

insert into book.employees (login, password)
values ('admin', '$2a$10$MzAyyJJDmK.h5S.HQEjV/eZVZfwj38Kf71A/EBeik06/ihsh2q1gm'); -- password - admin
