package com.milanzaric.fridge.service;


import com.milanzaric.fridge.dto.itemlocation.ItemLocationCreateDTO;
import com.milanzaric.fridge.dto.itemlocation.ItemLocationDTO;
import com.milanzaric.fridge.dto.itemlocation.ItemLocationUpdateDTO;
import com.milanzaric.fridge.entity.ItemLocation;
import com.milanzaric.fridge.mapper.ItemLocationMapper;
import com.milanzaric.fridge.repository.ItemLocationRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemLocationService {

    private final ItemLocationRepository itemLocationRepository;

    // GET ALL method, listing all Item locations
    public List<ItemLocationDTO> getAllItemLocations() {
        List<ItemLocation> itemLocations = itemLocationRepository.findAll();
        return ItemLocationMapper.MAPPER.mapToListItemLocationDTO(itemLocations);
    }

    // GET BY ID method, list Item location by id
    public ItemLocationDTO getItemLocationById(UUID id) {
        ItemLocation itemLocation = findItemLocationById(id);
        return ItemLocationMapper.MAPPER.mapToItemLocationDTO(itemLocation);
    }

    // CREATE method, creating new Item location
    // Added Transactional, all actions or rollback
    @Transactional
    public ItemLocationDTO createItemLocation(ItemLocationCreateDTO itemLocationCreateDTO) {
        ItemLocation itemLocation = ItemLocation.builder()
                .name(itemLocationCreateDTO.getName())
                .build();
        itemLocation = itemLocationRepository.save(itemLocation);
        return ItemLocationMapper.MAPPER.mapToItemLocationDTO(itemLocation);
    }

    // UPDATE method, updating existing Item location
    @Transactional
    public ItemLocationDTO updateItemLocation(ItemLocationUpdateDTO itemLocationUpdateDTO, UUID id) {
        ItemLocation itemLocation = findItemLocationById(id);

        if (itemLocationUpdateDTO.getName() != null) {
            itemLocation.setName(itemLocationUpdateDTO.getName());
        }
        itemLocation = itemLocationRepository.save(itemLocation);
        return ItemLocationMapper.MAPPER.mapToItemLocationDTO(itemLocation);
    }


    // findItemLocationById method
    private ItemLocation findItemLocationById(UUID id) {
        return itemLocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item location does not exists with id: " + id));

    }
}
