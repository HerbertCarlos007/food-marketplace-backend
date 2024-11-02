package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByProductIdAndUserIdAndStoreId(UUID productId, UUID userId, UUID storeId);

    @Query("SELECT c FROM Cart c WHERE c.storeId = :storeId AND c.userId = :userId")
    List<Cart> findByStoreIdAndUserId(@Param("storeId") UUID storeId, @Param("userId") UUID userId);
}
