package com.example.food_marketplace.repositories;

import com.example.food_marketplace.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
    Optional<Store> findBySubdomain(String subdomain);
}
