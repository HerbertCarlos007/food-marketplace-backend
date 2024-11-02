package com.example.food_marketplace.dto.cart;

import java.util.UUID;

public record ProductsCartResponseDTO(UUID id, String name, String imageUrl, double price) {
}
