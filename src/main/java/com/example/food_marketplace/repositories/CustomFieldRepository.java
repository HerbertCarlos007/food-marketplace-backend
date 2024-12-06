package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.customField.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CustomFieldRepository extends JpaRepository<CustomField, UUID> {
}
