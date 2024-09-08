package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, String> {
}
