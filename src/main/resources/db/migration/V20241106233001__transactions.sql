create table if not exists book.transactions
(
    id             bigserial primary key,
    type_operation boolean not null,
    date           timestamp without time zone default now(),
    reader_id      bigint ,
    constraint transactions_reader_fk
        foreign key (reader_id) references book.readers (id),
    book_id        bigint,
    constraint transactions_book_fk
        foreign key (book_id) references book.books (id)
);
create index if not exists transactions_reader_ix on book.transactions (reader_id);
create index if not exists transactions_book_ix on book.transactions (book_id);