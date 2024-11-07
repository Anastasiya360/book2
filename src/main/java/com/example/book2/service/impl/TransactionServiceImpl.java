package com.example.book2.service.impl;

import com.example.book2.entity.Book;
import com.example.book2.entity.Reader;
import com.example.book2.entity.Transaction;
import com.example.book2.exceptoin.ApiException;
import com.example.book2.repository.BookRepository;
import com.example.book2.repository.ReaderRepository;
import com.example.book2.repository.TransactionRepository;
import com.example.book2.service.TransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Override
    @Transactional
    public Transaction create(boolean typeOperation, Long readerId, Long bookId) {
        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setTypeOperation(typeOperation);

        if (bookId == null) {
            throw new ApiException("Идентификатор книги не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            throw new ApiException("Книга не найдена", HttpServletResponse.SC_NOT_FOUND);
        }
        transaction.setBook(book.get());

        if (readerId == null) {
            throw new ApiException("Идентификатор клиента не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        Optional<Reader> reader = readerRepository.findById(readerId);
        if (reader.isEmpty()) {
            throw new ApiException("Клиент не найден", HttpServletResponse.SC_NOT_FOUND);
        }
        transaction.setReader(reader.get());

        return transactionRepository.save(transaction);
    }
}
