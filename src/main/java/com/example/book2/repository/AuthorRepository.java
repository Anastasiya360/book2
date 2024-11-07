package com.example.book2.repository;

import com.example.book2.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = """
            select a1.*
            from book.authors a1
                     join (select authors.id, count(authors.id) as count_book
                           from book.authors
                                    join book.books_authors ba on authors.id = ba.author_id
                                    join book.transactions t on ba.book_id = t.book_id
                           where t.type_operation = true
                             and t.date between (:dateFrom) and (:dateTo)
                           group by authors.id
                           order by count_book desc
                           limit 1) a2 on a2.id = a1.id""")
    Optional<Author> findPopularAuthor(Timestamp dateFrom, Timestamp dateTo);
}
