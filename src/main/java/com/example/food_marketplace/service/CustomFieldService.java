package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.customField.CustomField;
import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.customField.CustomFieldRequestDTO;
import com.example.food_marketplace.dto.customField.CustomFieldResponseDTO;
import com.example.food_marketplace.repositories.CustomFieldRepository;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomFieldService {

    @Autowired
    private CustomFieldRepository customFieldRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private S3FileUploaderService s3FileUploader;

    public CustomField createCustomField(CustomFieldRequestDTO data) {
        String logoUrl = null;

        if (data.logoUrl() != null) {
            logoUrl = this.s3FileUploader.uploadImg(data.logoUrl());
        }

        Optional<Store> storeOptional = storeRepository.findById(data.storeId());
        if (storeOptional.isEmpty()) {
            throw new IllegalArgumentException("Store not found with ID");
        }
        Store store = storeOptional.get();

        CustomField newCustomField = new CustomField();
        newCustomField.setName(data.name());
        newCustomField.setPrimary_color(data.primary_color());
        newCustomField.setSecondary_color(data.secondary_color());
        newCustomField.setLogoUrl(logoUrl);
        newCustomField.setFont_name(data.font_name());
        newCustomField.setStore(store);

        customFieldRepository.save(newCustomField);
        return newCustomField;
    }

    public List<CustomFieldResponseDTO> getCustomFields(UUID storeId) {
        List<CustomField> customFields = customFieldRepository.findByStoreId(storeId);
        return customFields.stream().map(customField -> new CustomFieldResponseDTO(
                customField.getName(),
                customField.getPrimary_color(),
                customField.getSecondary_color(),
                customField.getLogoUrl(),
                customField.getFont_name(),
                customField.getStore().getId()
        )).toList();
    }
}
