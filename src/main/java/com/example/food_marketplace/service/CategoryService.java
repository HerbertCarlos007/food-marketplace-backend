package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.category.CategoryRequestDTO;
import com.example.food_marketplace.repositories.CategoryRepository;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StoreRepository storeRepository;

    public Category createCategory(CategoryRequestDTO data) {
        Optional<Category> categoryExists = categoryRepository.findByName(data.name());
        if (categoryExists.isPresent()) {
            return categoryExists.get();
        }

        Optional<Store> storeOptional = storeRepository.findById(data.storeId());
        if (storeOptional.isEmpty()) {
            throw new IllegalArgumentException("Store not found with ID: " + data.storeId());
        }
        Store store = storeOptional.get();

        Category categories = new Category();
        categories.setName(data.name());
        categories.setStore(store);
        return categoryRepository.save(categories);
    }
}
