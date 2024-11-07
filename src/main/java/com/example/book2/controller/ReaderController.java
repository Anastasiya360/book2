package com.example.book2.controller;

import com.example.book2.entity.Reader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reader")
@Tag(name = "readers")
@ApiResponse(responseCode = "" + HttpServletResponse.SC_INTERNAL_SERVER_ERROR, description = "Внутренняя ошибка сервера")
public interface ReaderController {

    @Operation(
            summary = "Get the most reading client",
            description = "Get the client who took the most books"
    )
    @GetMapping(path = "/get/popular")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reader.class)))
    })
    @PreAuthorize("hasAuthority('ADMIN')")
    Reader getPopularReader();

    @Operation(
            summary = "Get all clients who have not returned books",
            description = "Get all readers in descending order of number of undelivered books"
    )
    @GetMapping(path = "/get/by/return/books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK, description = "Запрос выполнен успешно",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reader.class)))
    })
    List<Reader> findReaderByReturnBooks();
}
