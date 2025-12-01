package com.milanzaric.fridge.controller;


import com.milanzaric.fridge.dto.itemlocation.ItemLocationCreateDTO;
import com.milanzaric.fridge.dto.itemlocation.ItemLocationDTO;
import com.milanzaric.fridge.dto.itemlocation.ItemLocationUpdateDTO;
import com.milanzaric.fridge.service.ItemLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge/item-locations")
public class ItemLocationController {

    private final ItemLocationService itemLocationService;

    @GetMapping
    public ResponseEntity<List<ItemLocationDTO>> getAllItemLocations() {
        List<ItemLocationDTO> itemLocationDTOS = itemLocationService.getAllItemLocations();
        return new ResponseEntity<>(itemLocationDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemLocationDTO> getItemLocationById(@PathVariable UUID id) {
        ItemLocationDTO itemLocationDTO = itemLocationService.getItemLocationById(id);
        return new ResponseEntity<>(itemLocationDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemLocationDTO> createItemLocation(@RequestBody ItemLocationCreateDTO itemLocationCreateDTO) {
        ItemLocationDTO itemLocationDTO = itemLocationService.createItemLocation(itemLocationCreateDTO);
        return new ResponseEntity<>(itemLocationDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemLocationDTO> updateItemLocation(@RequestBody ItemLocationUpdateDTO itemLocationUpdateDTO,
                                                              @PathVariable UUID id) {
        ItemLocationDTO itemLocationDTO = itemLocationService.updateItemLocation(itemLocationUpdateDTO, id);
        return new ResponseEntity<>(itemLocationDTO, HttpStatus.ACCEPTED);
    }
}
