package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.customField.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CustomFieldRepository extends JpaRepository<CustomField, UUID> {

    List<CustomField> findByStoreId(UUID storeId);

    void deleteByIdAndStoreId(UUID id, UUID storeId);
}
