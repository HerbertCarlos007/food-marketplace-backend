package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByProductIdAndUserIdAndStoreId(UUID productId, UUID userId, UUID storeId);
}
