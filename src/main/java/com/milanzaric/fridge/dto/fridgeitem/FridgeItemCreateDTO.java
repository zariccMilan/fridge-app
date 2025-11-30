package com.milanzaric.fridge.dto.fridgeitem;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FridgeItemCreateDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    private LocalDateTime storedAt;

    @NotNull
    private LocalDate bestBefore;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID locationId;
}
