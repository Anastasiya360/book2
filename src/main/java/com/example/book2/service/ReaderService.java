package com.example.book2.service;

import com.example.book2.entity.Reader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReaderService {

    Reader findPopularReader();

    List<Reader> findReaderByReturnBooks();
}
