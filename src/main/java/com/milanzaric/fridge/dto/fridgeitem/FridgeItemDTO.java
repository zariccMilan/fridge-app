package com.milanzaric.fridge.dto.fridgeitem;


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
public class FridgeItemDTO {

    private UUID id;
    private String name;
    private Integer quantity;
    private LocalDateTime storedAt;
    private LocalDate bestBefore;
    private LocalDateTime deletedAt;
    private Boolean isDeleted;
    private UUID categoryId;
    private String categoryName;
    private UUID locationId;
    private String locationName;
}
