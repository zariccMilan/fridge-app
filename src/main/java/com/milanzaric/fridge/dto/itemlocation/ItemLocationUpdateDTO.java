package com.milanzaric.fridge.dto.itemlocation;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemLocationUpdateDTO {

    @NotBlank
    @Size(max = 100)
    private String name;
}
