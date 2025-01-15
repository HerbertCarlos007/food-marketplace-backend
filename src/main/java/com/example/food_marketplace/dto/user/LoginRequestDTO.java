package com.example.food_marketplace.dto.user;

import java.util.UUID;

public record LoginRequestDTO (String email, String password, UUID storeId){
}
