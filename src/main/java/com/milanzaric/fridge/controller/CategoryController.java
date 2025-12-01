package com.milanzaric.fridge.controller;


import com.milanzaric.fridge.dto.category.CategoryCreateDTO;
import com.milanzaric.fridge.dto.category.CategoryDTO;
import com.milanzaric.fridge.dto.category.CategoryUpdateDTO;
import com.milanzaric.fridge.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable UUID id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryCreateDTO categoryCreateDTO) {
        CategoryDTO categoryDTO = categoryService.createCategory(categoryCreateDTO);
        return new ResponseEntity<>(categoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryUpdateDTO categoryUpdateDTO,
                                                      @PathVariable UUID id) {
        CategoryDTO categoryDTO = categoryService.updateCategory(categoryUpdateDTO, id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.ACCEPTED);
    }
}
