package com.example.food_marketplace.domain.customField;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "custom_fields")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomField {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String primary_color;
    private String secondary_color;
    private String logoUrl;
    private String font_name;
}
