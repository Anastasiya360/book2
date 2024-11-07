package com.example.book2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "book", name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Schema(description = "Название", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Column(name = "year")
    @Schema(description = "Год издания")
    private int year;

    @JsonIgnoreProperties(value = "books")
    @Fetch(FetchMode.JOIN)
    @Schema(description = "Авторы")
    @ManyToMany(targetEntity = Author.class)
    @JoinTable(schema = "book", name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors;
}
