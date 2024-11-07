package com.example.book2.service.impl;

import com.example.book2.entity.Reader;
import com.example.book2.repository.ReaderRepository;
import com.example.book2.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;

    @Override
    @Transactional(readOnly = true)
    public Reader findPopularReader() {
        return readerRepository.findPopularReader().get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reader> findReaderByReturnBooks() {
        return readerRepository.findReaderByReturnBooks();
    }
}
