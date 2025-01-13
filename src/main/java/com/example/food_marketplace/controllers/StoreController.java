package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.store.StoreRequestDTO;
import com.example.food_marketplace.dto.store.StoreResponseDTO;
import com.example.food_marketplace.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Store> createStore(@RequestParam("subdomain") String subdomain,
                                             @RequestParam("name") String name,
                                             @RequestParam(value = "imageUrl", required = false)MultipartFile imageUrl) {
        StoreRequestDTO storeRequestDTO = new StoreRequestDTO(subdomain, name, imageUrl);
        Store store = this.storeService.createStore(storeRequestDTO);
        return ResponseEntity.ok(store);
    }

    @GetMapping("/{subdomain}")
    public ResponseEntity<StoreResponseDTO> getBySubdomain(@PathVariable String subdomain) {
        Store store = storeService.getBySubdomain(subdomain);
        return ResponseEntity.ok(new StoreResponseDTO(store.getSubdomain(), store.getId()));
    }
}
