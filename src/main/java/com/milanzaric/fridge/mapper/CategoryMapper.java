package com.milanzaric.fridge.mapper;


import com.milanzaric.fridge.dto.category.CategoryDTO;
import com.milanzaric.fridge.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO mapToCategoryDTO(Category category);

    List<CategoryDTO> mapToListCategoryDTO(List<Category> categories);
}
