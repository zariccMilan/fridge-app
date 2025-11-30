package com.milanzaric.fridge.mapper;


import com.milanzaric.fridge.dto.itemlocation.ItemLocationDTO;
import com.milanzaric.fridge.entity.ItemLocation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemLocationMapper {

    ItemLocationMapper MAPPER = Mappers.getMapper(ItemLocationMapper.class);

    ItemLocationDTO mapToItemLocationDTO(ItemLocation itemLocation);

    List<ItemLocationDTO> mapToListItemLocationDTO(List<ItemLocation> itemLocations);
}
