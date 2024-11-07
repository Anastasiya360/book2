package com.example.book2.service.impl;

import com.example.book2.entity.Author;
import com.example.book2.exceptoin.ApiException;
import com.example.book2.repository.AuthorRepository;
import com.example.book2.service.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Author findPopularAuthor(LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo)) {
            throw new ApiException("Даты заданы неверно", HttpServletResponse.SC_BAD_REQUEST);
        }
        return authorRepository.findPopularAuthor(
                        Timestamp.valueOf(dateFrom.atStartOfDay()),
                        Timestamp.valueOf(dateTo.atStartOfDay()))
                .orElse(null);
    }
}
