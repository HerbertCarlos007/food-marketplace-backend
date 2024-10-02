package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name);
}
