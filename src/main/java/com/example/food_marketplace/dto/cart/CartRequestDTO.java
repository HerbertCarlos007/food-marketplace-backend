package com.example.food_marketplace.dto.cart;

import java.util.UUID;

public record CartRequestDTO(UUID productId, UUID storeId, UUID userId) {
}
