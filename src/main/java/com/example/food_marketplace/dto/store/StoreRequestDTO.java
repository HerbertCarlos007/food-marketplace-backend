package com.example.food_marketplace.dto.store;

import org.springframework.web.multipart.MultipartFile;

public record StoreRequestDTO (String subdomain, String name, MultipartFile imageUrl){
}
