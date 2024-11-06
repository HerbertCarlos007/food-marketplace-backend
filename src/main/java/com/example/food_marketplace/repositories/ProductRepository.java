package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByStoreId(UUID storeId);

    void deleteByIdAndStoreId(UUID id, UUID storeId);
}
