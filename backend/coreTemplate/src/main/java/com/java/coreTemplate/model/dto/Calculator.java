package com.java.coreTemplate.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "calculator")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Calculator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_scientific")
    private boolean isScientific;

    @Column(name = "last_used_at")
    private Instant lastUsedAt;

    @Column(name = "memory_value")
    private Double memoryValue;

    @Version
    private Long version;

    // Custom getter for Optional field
    public Optional<Double> getMemoryValue() {
        return Optional.ofNullable(memoryValue);
    }

    // Custom getter for Optional timestamp
    public Optional<Instant> getLastUsedAt() {
        return Optional.ofNullable(lastUsedAt);
    }
}