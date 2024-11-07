package com.example.book2.repository;

import com.example.book2.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query(nativeQuery = true, value = """
            select a1.*
            from book.readers a1
                     join (select readers.id, count(readers.id) as count_reader from book.readers
                            join book.transactions t on readers.id = t.reader_id
                        where type_operation = true
                        group by readers.id
                        order by count_reader desc
                        limit 1) a2 on a2.id = a1.id""")
    Optional<Reader> findPopularReader();

    @Query(nativeQuery = true, value = """
            select a1.*
            from book.readers a1
                     join (select readers.id, count(readers.id) as count_reader
                           from book.readers
                                    join book.transactions t on readers.id = t.reader_id
                           where type_operation = false
                           group by readers.id
                           ) a2 on a2.id = a1.id
                     order by count_reader desc""")
    List<Reader> findReaderByReturnBooks();
}
