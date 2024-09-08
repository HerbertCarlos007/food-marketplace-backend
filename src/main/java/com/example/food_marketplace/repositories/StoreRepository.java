package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, String> {
    Optional<Store> findBySubdomain(String subdomain);
}
