package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.store.StoreRequestDTO;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(StoreRequestDTO data) {
        Store store = new Store();
        store.setSubdomain(data.subdomain());

        return storeRepository.save(store);
    }
}
