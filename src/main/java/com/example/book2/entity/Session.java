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
@Table(schema = "book", name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "access_token")
    @Schema(description = "Access token", requiredMode = Schema.RequiredMode.REQUIRED)
    private String accessToken;

    @Column(name = "refresh_token")
    @Schema(description = "Refresh token", requiredMode = Schema.RequiredMode.REQUIRED)
    private String refreshToken;

    @JsonIgnore
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "user_id")
    private Long userId;
}
