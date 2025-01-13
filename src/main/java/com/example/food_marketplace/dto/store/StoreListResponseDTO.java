package com.example.food_marketplace.dto.store;

import java.util.UUID;

public record StoreListResponseDTO(UUID id , String name, String imageUrl, String subdomain) {
}
