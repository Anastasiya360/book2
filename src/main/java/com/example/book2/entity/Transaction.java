package com.example.book2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "book", name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_operation")
    @Schema(description = "Тип операции(true - взятие/false - возврат)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean typeOperation;

    @JsonIgnore
    @Column(name = "date")
    @Schema(description = "Дата и время операции", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime date;

    @JoinColumn(name = "reader_id")
    @Schema(description = "Клиент", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne(targetEntity = Reader.class, cascade = CascadeType.DETACH)
    private Reader reader;

    @JoinColumn(name = "book_id")
    @Schema(description = "Книга", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.DETACH)
    private Book book;
}
