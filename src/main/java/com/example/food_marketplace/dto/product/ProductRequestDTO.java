package com.example.food_marketplace.dto.product;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ProductRequestDTO (String name, MultipartFile imageUrl, double price, UUID storeId, String inStock, String productType, UUID categoryId, String accompaniments){
}
