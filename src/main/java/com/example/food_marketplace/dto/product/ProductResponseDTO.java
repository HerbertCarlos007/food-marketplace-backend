package com.example.food_marketplace.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ProductResponseDTO(UUID id ,String name, String imageUrl, double price, UUID storeId, String status, String productType) {

}
