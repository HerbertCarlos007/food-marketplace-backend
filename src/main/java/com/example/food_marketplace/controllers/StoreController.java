package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.store.StoreRequestDTO;
import com.example.food_marketplace.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreRequestDTO data) {
        Store stores = storeService.createStore(data);
        return ResponseEntity.ok(stores);
    }
}
