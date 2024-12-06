package com.example.food_marketplace.dto.customField;

import org.springframework.web.multipart.MultipartFile;

public record CustomFieldRequestDTO(String name, String primary_color, String secondary_color, MultipartFile logoUrl, String font_name) {
}
