package com.example.food_marketplace.domain.store;

import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "stores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private  String name;
    private String imageUrl;
    private String subdomain;

    @OneToMany(mappedBy = "store")
    private List<User> users;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    @OneToMany(mappedBy = "store")
    private List<Category> categories;
}
