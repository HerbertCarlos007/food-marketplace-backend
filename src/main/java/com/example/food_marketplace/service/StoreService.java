package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.store.StoreRequestDTO;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(StoreRequestDTO data) {
        Optional<Store> storeExists = storeRepository.findBySubdomain(data.subdomain());
        if (storeExists.isPresent()) {
            return storeExists.get();
        }

        Store stores = new Store();
        stores.setSubdomain(data.subdomain());
        return storeRepository.save(stores);
    }
}
