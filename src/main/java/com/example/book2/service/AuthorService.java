package com.example.book2.service;

import com.example.book2.entity.Author;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface AuthorService {

    Author findPopularAuthor(LocalDate dateFrom, LocalDate dateTo);
}
