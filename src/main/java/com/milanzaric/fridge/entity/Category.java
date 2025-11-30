package com.milanzaric.fridge.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY) // default is LAZY but for clarification
    private List<FridgeItem> fridgeItems;
}
