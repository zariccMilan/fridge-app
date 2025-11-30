package com.milanzaric.fridge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fridge_items")
public class FridgeItem {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Min(0)
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @NotNull
    @Column(name = "stored_at", nullable = false)
    private LocalDateTime storedAt;

    @NotNull
    @Column(name = "best_before", nullable = false)
    private LocalDate bestBefore;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private ItemLocation itemLocation;
}
