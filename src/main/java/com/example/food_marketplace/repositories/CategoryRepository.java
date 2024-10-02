package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
