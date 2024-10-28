package com.example.food_marketplace.domain.product;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "products")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String imageUrl;
    private double price;
    private String status;
    private String productType;
    private String accompaniments;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
