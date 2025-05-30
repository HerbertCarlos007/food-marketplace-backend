package com.example.food_marketplace.dto.customField;

import java.util.UUID;

public record CustomFieldResponseDTO(UUID id, String name, String primary_color, String secondary_color, String logoUrl, String font_name, UUID storeId) {
}
