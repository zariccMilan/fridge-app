package com.milanzaric.fridge.dto.itemlocation;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemLocationDTO {

    private UUID id;
    private String name;
}
