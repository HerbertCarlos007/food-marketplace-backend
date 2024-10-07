package com.example.food_marketplace.dto.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {
}
