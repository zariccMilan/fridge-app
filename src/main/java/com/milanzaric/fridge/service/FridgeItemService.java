package com.milanzaric.fridge.service;


import com.milanzaric.fridge.dto.fridgeitem.FridgeItemCreateDTO;
import com.milanzaric.fridge.dto.fridgeitem.FridgeItemDTO;
import com.milanzaric.fridge.dto.fridgeitem.FridgeItemUpdateDTO;
import com.milanzaric.fridge.entity.Category;
import com.milanzaric.fridge.entity.FridgeItem;
import com.milanzaric.fridge.entity.ItemLocation;
import com.milanzaric.fridge.mapper.FridgeItemMapper;
import com.milanzaric.fridge.repository.CategoryRepository;
import com.milanzaric.fridge.repository.FridgeItemRepository;
import com.milanzaric.fridge.repository.ItemLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FridgeItemService {

    private final FridgeItemRepository fridgeItemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemLocationRepository itemLocationRepository;


    // GET ALL method, listing all Fridge items
    public List<FridgeItemDTO> getAllFridgeItems() {
        List<FridgeItem> fridgeItems = fridgeItemRepository.findAllByIsDeletedFalse();
        return FridgeItemMapper.MAPPER.mapToListFridgeItemDTO(fridgeItems);
    }

    // GET BY ID method, list Fridge item by id
    public FridgeItemDTO getFridgeItemById(UUID id) {
        FridgeItem fridgeItem = findFridgeItemById(id);
        return FridgeItemMapper.MAPPER.mapToFridgeItemDTO(fridgeItem);
    }

    // CREATE method, creating new Fridge item
    // Added Transactional, all actions or rollback
    @Transactional
    public FridgeItemDTO createFridgeItem(FridgeItemCreateDTO fridgeItemCreateDTO) {
        Category category = categoryRepository.findById(fridgeItemCreateDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        ItemLocation itemLocation = itemLocationRepository.findById(fridgeItemCreateDTO.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Item location not found"));

        FridgeItem fridgeItem = FridgeItem.builder()
                .name(fridgeItemCreateDTO.getName())
                .quantity(fridgeItemCreateDTO.getQuantity())
                .storedAt(LocalDateTime.now())
                .bestBefore(fridgeItemCreateDTO.getBestBefore())
                .category(category)
                .itemLocation(itemLocation)
                .build();

        fridgeItem = fridgeItemRepository.save(fridgeItem);
        return FridgeItemMapper.MAPPER.mapToFridgeItemDTO(fridgeItem);
    }

    // UPDATE method, updating existing Fridge items
    @Transactional
    public FridgeItemDTO updateFridgeItem(FridgeItemUpdateDTO fridgeItemUpdateDTO, UUID id) {
        FridgeItem fridgeItem = findFridgeItemById(id);

        if (fridgeItemUpdateDTO.getName() != null) {
            fridgeItem.setName(fridgeItemUpdateDTO.getName());
        }
        if (fridgeItemUpdateDTO.getQuantity() != null) {
            fridgeItem.setQuantity(fridgeItemUpdateDTO.getQuantity());
        }
        if (fridgeItemUpdateDTO.getStoredAt() != null) {
            fridgeItem.setStoredAt(fridgeItemUpdateDTO.getStoredAt());
        }
        if (fridgeItemUpdateDTO.getBestBefore() != null) {
            fridgeItem.setBestBefore(fridgeItemUpdateDTO.getBestBefore());
        }
        if (fridgeItemUpdateDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(fridgeItemUpdateDTO.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found"));
            fridgeItem.setCategory(category);
        }
        if (fridgeItemUpdateDTO.getLocationId() != null) {
            ItemLocation itemLocation = itemLocationRepository.findById(fridgeItemUpdateDTO.getLocationId())
                    .orElseThrow(() -> new EntityNotFoundException("Item location not found"));
            fridgeItem.setItemLocation(itemLocation);
        }

        fridgeItem = fridgeItemRepository.save(fridgeItem);
        return FridgeItemMapper.MAPPER.mapToFridgeItemDTO(fridgeItem);
    }

    // SOFT DELETE method, deleting existing Fridge item
    @Transactional
    public void softDeleteFridgeItem(UUID id) {
        FridgeItem fridgeItem = findFridgeItemById(id);
        if (!fridgeItem.isDeleted()) {
            fridgeItem.setDeleted(true);
            fridgeItem.setDeletedAt(LocalDateTime.now());
        }
    }


    // findFridgeItemById method
    private FridgeItem findFridgeItemById(UUID id) {
        return fridgeItemRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("Fridge item does not exists with id: " + id));
    }
}
