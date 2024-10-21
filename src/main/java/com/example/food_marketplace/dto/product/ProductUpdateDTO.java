package com.example.food_marketplace.dto.product;

import java.util.UUID;

public record ProductUpdateDTO(String name, double price, String status, String productType, UUID categoryId) {
}
