package com.example.food_marketplace.dto.category;

import java.util.UUID;

public record CategoryRequestDTO(String name, UUID storeId) {
}
