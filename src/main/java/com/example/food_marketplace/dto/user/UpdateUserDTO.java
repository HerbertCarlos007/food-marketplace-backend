package com.example.food_marketplace.dto.user;

import java.util.UUID;

public record UpdateUserDTO(String name, String email, String role, String status) {
}
