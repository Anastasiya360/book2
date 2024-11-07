package com.example.book2.controller;

import com.example.book2.dto.AuthRequestDto;
import com.example.book2.entity.Session;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth")
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface AuthController {

    @Operation(
            summary = "Authorization"
    )
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Session.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Логин не передан
                            * Пароль не передан"""),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_FORBIDDEN, description = "Пароль не верный"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Сотрудник с данным логином не найден"),
    })
    Session authorization(
            @Parameter(description = "Данные для авторизации", required = true)
            @RequestBody AuthRequestDto authRequestDto);

    @Operation(
            summary = "Refresh session"
    )
    @PostMapping("/refresh")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Session.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST, description = "Refresh token не передан"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND, description = "Сессия с данным refresh token не найдена"),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_METHOD_NOT_ALLOWED, description = "Срок жизни refresh token истек"),
    })
    Session refreshSession(
            @Parameter(description = "Refresh token", required = true)
            @RequestParam String refreshToken);
}
