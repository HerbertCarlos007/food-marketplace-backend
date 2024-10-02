package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.dto.category.CategoryRequestDTO;
import com.example.food_marketplace.dto.category.CategoryResponseDTO;
import com.example.food_marketplace.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDTO data) {
        Category categories = categoryService.createCategory(data);
        return ResponseEntity.ok(categories);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getCategories() {
        List<CategoryResponseDTO> categories = categoryService.getCategories();
        return  ResponseEntity.ok(categories);
    }

}
