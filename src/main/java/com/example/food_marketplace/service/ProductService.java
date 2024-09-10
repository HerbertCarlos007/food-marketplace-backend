package com.example.food_marketplace.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.product.ProductRequestDTO;
import com.example.food_marketplace.repositories.ProductRepository;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private S3FileUploaderServiceService s3FileUploader;

    public Product createProduct(ProductRequestDTO data) {
        String imgUrl = null;

        if(data.imageUrl() != null) {
            imgUrl = this.s3FileUploader.uploadImg(data.imageUrl());
        }

        Optional<Store> storeOptional = storeRepository.findById(data.storeId());
        if (storeOptional.isEmpty()) {
            throw new IllegalArgumentException("Store not found with ID: " + data.storeId());
        }
        Store store = storeOptional.get();

        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setImageUrl(imgUrl);
        newProduct.setPrice(data.price());
        newProduct.setStore(store);

        productRepository.save(newProduct);
        return newProduct;
    }
}
