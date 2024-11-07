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
@Table(schema = "book", name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Schema(description = "Имя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Column(name = "surname")
    @Schema(description = "Фамилия", requiredMode = Schema.RequiredMode.REQUIRED)
    private String surname;

    @Column(name = "date_of_birth")
    @Schema(description = "Дата рождения")
    private String dateOfBirth;

    @JsonIgnoreProperties(value = "authors")
    @Fetch(FetchMode.JOIN)
    @Schema(description = "Книги")
    @ManyToMany(targetEntity = Book.class)
    @JoinTable(schema = "book", name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;
}
