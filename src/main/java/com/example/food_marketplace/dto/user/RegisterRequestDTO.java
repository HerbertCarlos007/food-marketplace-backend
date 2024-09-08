package com.example.food_marketplace.dto.user;

import java.util.UUID;

public record RegisterRequestDTO(String name, String email, String password, UUID storeId) {
}