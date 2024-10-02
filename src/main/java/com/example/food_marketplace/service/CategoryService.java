package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.category.CategoryRequestDTO;
import com.example.food_marketplace.dto.category.CategoryResponseDTO;
import com.example.food_marketplace.repositories.CategoryRepository;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Category createCategory(CategoryRequestDTO data) {
        Optional<Store> storeOptional = storeRepository.findById(data.storeId());
        if (!storeOptional.isPresent()) {
            throw new IllegalArgumentException("Store not found with ID: " + data.storeId());
        }
        Store store = storeOptional.get();

        Category category = new Category();
        category.setName(data.name());
        category.setStore(store);
        return categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getStore().getId()
        )).toList();
    }

}
