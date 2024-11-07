create table if not exists book.sessions
(
    id            bigserial primary key,
    access_token  varchar not null,
    refresh_token varchar not null,
    date          timestamp without time zone default now(),
    user_id       bigint  not null
);
