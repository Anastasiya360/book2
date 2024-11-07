create table if not exists book.books_authors
(
    book_id bigint not null,
    author_id       bigint not null,
    constraint books_authors_pkey primary key (book_id, author_id),
    constraint books_authors_book_fk foreign key (book_id)
        references book.books (id) match simple
        on update cascade
        on delete cascade,
    constraint books_authors_author_fk foreign key (author_id)
        references book.authors (id) match simple
        on update cascade
        on delete cascade
);
create index if not exists books_authors_book_ix on book.books_authors (book_id);
create index if not exists books_authors_author_ix on book.books_authors (author_id);