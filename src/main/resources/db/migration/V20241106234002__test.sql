insert into book.authors (name, surname, date_of_birth) values ('Александр','Пушкин', '06.06.1799');
insert into book.authors (name, surname, date_of_birth) values ('Лев','Толстой', '09.09.1828');

insert into book.books (name, year) values ('Евгений Онегин', 2009);
insert into book.books (name, year) values ('Капитанская дочка', 2010);
insert into book.books (name, year) values ('Война и мир', 2019);
insert into book.books (name, year) values ('Анна Каренина', 2012);

insert into book.books_authors (book_id, author_id) values (1,1);
insert into book.books_authors (book_id, author_id) values (2,1);
insert into book.books_authors (book_id, author_id) values (3,2);
insert into book.books_authors (book_id, author_id) values (4,2);

insert into book.readers (phone_number, name, surname, gender, date_of_birth) values ('8(918)346-78-90','Иван','Васильев',true, '24.06.1999');
insert into book.readers (phone_number, name, surname, gender, date_of_birth) values ('8(988)346-68-34','Константин','Петров',true, '12.07.1993');