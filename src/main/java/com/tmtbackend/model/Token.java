package com.tmtbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "token_seq_gen")
    @TableGenerator(
            name = "token_seq_gen",
            table = "token_seq",          // Name of the sequence table
            pkColumnName = "seq_name",    // Primary key column name
            valueColumnName = "next_val", // Column to store the next value
            pkColumnValue = "token",      // Identifier for the sequence for this entity
            allocationSize = 1
    )
    private int id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
