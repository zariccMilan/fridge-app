package com.milanzaric.fridge.service;

import com.milanzaric.fridge.dto.category.CategoryCreateDTO;
import com.milanzaric.fridge.dto.category.CategoryDTO;
import com.milanzaric.fridge.dto.category.CategoryUpdateDTO;
import com.milanzaric.fridge.entity.Category;
import com.milanzaric.fridge.mapper.CategoryMapper;
import com.milanzaric.fridge.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // GET ALL method, listing all Categories
    @Cacheable("categories")
    public List<CategoryDTO> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        System.out.println("Loading from DB");
        return CategoryMapper.MAPPER.mapToListCategoryDTO(category);
    }

    // GET BY ID method, list Category by id
    public CategoryDTO getCategoryById(UUID id) {
        Category category = findCategoryById(id);
        return CategoryMapper.MAPPER.mapToCategoryDTO(category);
    }

    // CREATE method, creating new Category
    // Added Transactional, all actions or rollback
    @CacheEvict(value = "categories", allEntries = true)
    @Transactional
    public CategoryDTO createCategory(CategoryCreateDTO categoryCreateDTO) {
        Category category = Category.builder()
                .name(categoryCreateDTO.getName())
                .build();
        category = categoryRepository.save(category);
        return CategoryMapper.MAPPER.mapToCategoryDTO(category);
    }

    // UPDATE method, updating existing Category
    @CacheEvict(value = "categories", allEntries = true)
    @Transactional
    public CategoryDTO updateCategory(CategoryUpdateDTO categoryUpdateDTO, UUID id) {
        Category category = findCategoryById(id);

        if (categoryUpdateDTO.getName() != null) {
            category.setName(categoryUpdateDTO.getName());
        }

        category = categoryRepository.save(category);
        return CategoryMapper.MAPPER.mapToCategoryDTO(category);
    }

    // ADD SOFT DELETE, ALSO IN DATABASE !

    // findCategoryById method
    private Category findCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category does not exists with id: " + id));
    }
}
