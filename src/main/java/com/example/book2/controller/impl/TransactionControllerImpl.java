package com.example.book2.controller.impl;

import com.example.book2.controller.TransactionController;
import com.example.book2.entity.Transaction;
import com.example.book2.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionControllerImpl implements TransactionController {

    private final TransactionService transactionService;

    @Override
    public Transaction create(boolean typeOperation, Long readerId, Long bookId) {
        return transactionService.create(typeOperation, readerId, bookId);
    }
}
