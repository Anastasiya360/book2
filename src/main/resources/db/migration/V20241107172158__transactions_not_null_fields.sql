alter table if exists book.transactions
    alter column reader_id set not null,
    alter column book_id set not null;
