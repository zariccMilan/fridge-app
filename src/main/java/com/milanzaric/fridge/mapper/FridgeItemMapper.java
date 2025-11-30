package com.milanzaric.fridge.mapper;


import com.milanzaric.fridge.dto.fridgeitem.FridgeItemDTO;
import com.milanzaric.fridge.entity.FridgeItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FridgeItemMapper {

    FridgeItemMapper MAPPER = Mappers.getMapper(FridgeItemMapper.class);


    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "itemLocation.id", target = "locationId")
    @Mapping(source = "itemLocation.name", target = "locationName")
    FridgeItemDTO mapToFridgeItemDTO(FridgeItem fridgeItem);

    List<FridgeItemDTO> mapToListFridgeItemDTO(List<FridgeItem> fridgeItems);
}
