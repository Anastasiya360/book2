package com.example.book2.controller.impl;

import com.example.book2.controller.AuthorController;
import com.example.book2.entity.Author;
import com.example.book2.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    public Author findPopularAuthor(LocalDate dateFrom, LocalDate dateTo) {
        return authorService.findPopularAuthor(dateFrom, dateTo);
    }
}
