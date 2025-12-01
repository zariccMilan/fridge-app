package com.milanzaric.fridge.controller;

import com.milanzaric.fridge.dto.fridgeitem.FridgeItemCreateDTO;
import com.milanzaric.fridge.dto.fridgeitem.FridgeItemDTO;
import com.milanzaric.fridge.dto.fridgeitem.FridgeItemUpdateDTO;
import com.milanzaric.fridge.service.FridgeItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge/fridge-items")
public class FridgeItemController {


    private final FridgeItemService fridgeItemService;

    @GetMapping
    public ResponseEntity<List<FridgeItemDTO>> getAllFridgeItems() {
        List<FridgeItemDTO> fridgeItems = fridgeItemService.getAllFridgeItems();
        return new ResponseEntity<>(fridgeItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FridgeItemDTO> getFridgeItemById(@PathVariable UUID id) {
        FridgeItemDTO fridgeItemDTO = fridgeItemService.getFridgeItemById(id);
        return new ResponseEntity<>(fridgeItemDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FridgeItemDTO> createFridgeItem(@RequestBody FridgeItemCreateDTO fridgeItemCreateDTO) {
        FridgeItemDTO fridgeItemDTO = fridgeItemService.createFridgeItem(fridgeItemCreateDTO);
        return new ResponseEntity<>(fridgeItemDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FridgeItemDTO> updateFridgeItem(@RequestBody FridgeItemUpdateDTO fridgeItemUpdateDTO,
                                                          @PathVariable UUID id) {
        FridgeItemDTO fridgeItemDTO = fridgeItemService.updateFridgeItem(fridgeItemUpdateDTO, id);
        return new ResponseEntity<>(fridgeItemDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteFridgeItem(@PathVariable UUID id) {
        fridgeItemService.softDeleteFridgeItem(id);
    }
}

