package com.example.food_marketplace.dto.customField;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CustomFieldResponseDTO(String name, String primary_color, String secondary_color, String logoUrl, String font_name, UUID storeId) {
}
