package com.example.book2.controller;

import com.example.book2.entity.Transaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@Tag(name = "transactions")
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface TransactionController {

    @Operation(
            summary = "Create transaction"
    )
    @PostMapping(path = "/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_BAD_REQUEST,
                    description = """
                            * Идентификатор книги не передан
                            * Идентификатор клиента не передан
                            """),
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_NOT_FOUND,
                    description = """
                            * Книга не найдена
                            * Клиент не найден
                            """)
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    Transaction create(
            @Parameter(description = "Тип операции(true - взятие/false - возврат)", required = true)
            @RequestParam("typeOperation") boolean typeOperation,
            @Parameter(description = "Идентификатор клиента")
            @RequestParam("readerId") Long readerId,
            @Parameter(description = "Идентификатор книги")
            @RequestParam("bookId") Long bookId);
}
