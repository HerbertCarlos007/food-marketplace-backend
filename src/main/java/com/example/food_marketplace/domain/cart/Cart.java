package com.example.food_marketplace.domain.cart;

import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.domain.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID productId;
    private UUID storeId;
    private UUID userId;
    private Integer quantity;

}
