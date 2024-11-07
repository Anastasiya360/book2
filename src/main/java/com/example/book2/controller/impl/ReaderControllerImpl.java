package com.example.book2.controller.impl;

import com.example.book2.controller.ReaderController;
import com.example.book2.entity.Reader;
import com.example.book2.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReaderControllerImpl implements ReaderController {

    private final ReaderService readerService;

    @Override
    public Reader getPopularReader() {
        return readerService.findPopularReader();
    }

    @Override
    public List<Reader> findReaderByReturnBooks() {
        return readerService.findReaderByReturnBooks();
    }
}
