package com.example.food_marketplace.dto.store;

import java.util.UUID;

public record StoreListDTO(UUID id , String name, String imageUrl, String subdomain) {
}
