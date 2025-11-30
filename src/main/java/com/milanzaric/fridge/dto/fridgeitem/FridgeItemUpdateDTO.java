package com.milanzaric.fridge.dto.fridgeitem;


import jakarta.validation.constraints.Min;
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
public class FridgeItemUpdateDTO {

    @Size(max = 50)
    private String name;

    @Min(0)
    private Integer quantity;
    private LocalDateTime storedAt;
    private LocalDate bestBefore;
    private UUID categoryId;
    private UUID locationId;
}
