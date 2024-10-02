package com.example.food_marketplace.dto.category;

import java.util.UUID;

public record CategoryResponseDTO(UUID id, String name, UUID storeId) {
}
