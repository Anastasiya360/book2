package com.example.book2.service;

import com.example.book2.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {

    Transaction create(boolean typeOperation, Long readerId, Long bookId);
}
