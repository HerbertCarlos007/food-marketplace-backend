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

    @Autowired
    private S3FileUploaderService s3FileUploader;

    public Store createStore(StoreRequestDTO data) {
        String imgUrl = null;

        if(data.imageUrl() != null) {
            imgUrl = this.s3FileUploader.uploadImg(data.imageUrl());
        }

        Optional<Store> storeExists = storeRepository.findBySubdomain(data.subdomain());
        if (storeExists.isPresent()) {
            return storeExists.get();
        }

        Store stores = new Store();
        stores.setSubdomain(data.subdomain());
        stores.setName(data.name());
        stores.setImageUrl(imgUrl);
        return storeRepository.save(stores);
    }

    public Store getBySubdomain(String subdomain) {
        Store store = storeRepository.findBySubdomain(subdomain)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        return store;
    }
}
