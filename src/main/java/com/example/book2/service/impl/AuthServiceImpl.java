package com.example.book2.service.impl;

import com.example.book2.entity.Employee;
import com.example.book2.entity.Session;
import com.example.book2.exceptoin.ApiException;
import com.example.book2.repository.EmployeeRepository;
import com.example.book2.repository.SessionRepository;
import com.example.book2.security.JwtTokenUtil;
import com.example.book2.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.example.book2.security.JwtFilter.COOKIE_NAME;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Override
    @Transactional
    public Session authorization(String login, String password) {
        if ((login == null) || login.isBlank()) {
            throw new ApiException("Логин не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        if ((password == null) || password.isBlank()) {
            throw new ApiException("Пароль не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        Optional<Employee> employee = employeeRepository.findByLogin(login);
        if (employee.isEmpty()) {
            throw new ApiException("Сотрудник с данным логином не найден", HttpServletResponse.SC_NOT_FOUND);
        }
        if (!bCryptPasswordEncoder.matches(password, employee.get().getPassword())) {
            throw new ApiException("Пароль не верный", HttpServletResponse.SC_FORBIDDEN);
        }
        Session session = new Session();
        session.setUserId(employee.get().getId());
        refreshSession(session, employee.get());
        return session;
    }

    @Override
    @Transactional
    public Session refreshSession(String refreshToken) {
        if ((refreshToken == null) || refreshToken.isBlank()) {
            throw new ApiException("Refresh token не передан", HttpServletResponse.SC_BAD_REQUEST);
        }
        Optional<Session> session = sessionRepository.findByRefreshToken(refreshToken);
        if (session.isEmpty()) {
            throw new ApiException("Сессия с данным refresh token не найдена", HttpServletResponse.SC_NOT_FOUND);
        }
        if (session.get().getDate().plusMinutes(30).isBefore(LocalDateTime.now())) {
            throw new ApiException("Срок жизни refresh token истек", HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        refreshSession(session.get(), employeeRepository.findById(session.get().getUserId()).orElseThrow());
        return session.get();
    }

    private void refreshSession(Session session, Employee employee) {
        // Generate a new access token for the session
        session.setAccessToken(jwtTokenUtil.generateToken(
                employee.getId(), employee.getId().toString(), request.getRequestURL().toString(), Set.of("ADMIN")));

        // Generate a new refresh token for the session
        session.setRefreshToken(UUID.randomUUID().toString());

        session.setDate(LocalDateTime.now());

        // Save the updated session in the repository
        sessionRepository.save(session);

        // Create a new cookie with the access token
        Cookie cookie = new Cookie(COOKIE_NAME, session.getAccessToken());
        cookie.setPath("/");
        cookie.setMaxAge(JwtTokenUtil.ACCESS_TOKEN_VALIDITY * 60);
        cookie.setHttpOnly(true);

        // Set the cookie as secure if the request was made over HTTPS
        if ("https".equalsIgnoreCase(request.getScheme()))
            cookie.setSecure(true);

        // Add the cookie to the response
        response.addCookie(cookie);
    }
}
