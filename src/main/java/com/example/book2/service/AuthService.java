package com.example.book2.service;

import com.example.book2.entity.Session;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    Session authorization(String login, String password);

    Session refreshSession(String refreshToken);
}
