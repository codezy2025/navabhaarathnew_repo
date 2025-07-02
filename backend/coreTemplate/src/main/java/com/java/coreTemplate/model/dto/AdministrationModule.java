package com.java.coreTemplate.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "administration_module")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdministrationModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "module_name", nullable = false, length = 100)
    private String moduleName;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_system_module")
    private boolean isSystemModule;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "access_level", length = 50)
    private String accessLevel;

    @Version
    private Long version;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    // Custom getter for Optional description
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    // Custom getter for Optional accessLevel
    public Optional<String> getAccessLevel() {
        return Optional.ofNullable(accessLevel);
    }
}