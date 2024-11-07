package com.example.book2.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "book", name = "readers")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    @Schema(description = "Номер телефона", requiredMode = Schema.RequiredMode.REQUIRED)
    private String phoneNumber;

    @Column(name = "name")
    @Schema(description = "Имя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Column(name = "surname")
    @Schema(description = "Фамилия")
    private String surname;

    @Column(name = "gender")
    @Schema(description = "Пол(true - мужской; false - женский")
    private Boolean gender;

    @Column(name = "date_of_birth")
    @Schema(description = "Дата рождения")
    private String dateOfBirth;
}
