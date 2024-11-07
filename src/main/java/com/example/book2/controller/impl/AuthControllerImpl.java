package com.example.book2.controller.impl;

import com.example.book2.controller.AuthController;
import com.example.book2.dto.AuthRequestDto;
import com.example.book2.entity.Session;
import com.example.book2.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public Session authorization(AuthRequestDto authRequestDto) {
        return authService.authorization(authRequestDto.getLogin(), authRequestDto.getPassword());
    }

    @Override
    public Session refreshSession(String refreshToken) {
        return authService.refreshSession(refreshToken);
    }
}
