package com.example.food_marketplace.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.food_marketplace.domain.category.Category;
import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.dto.product.ProductRequestDTO;
import com.example.food_marketplace.dto.product.ProductResponseDTO;
import com.example.food_marketplace.dto.product.ProductUpdateDTO;
import com.example.food_marketplace.repositories.CategoryRepository;
import com.example.food_marketplace.repositories.ProductRepository;
import com.example.food_marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private  CategoryRepository categoryRepository;

    @Autowired
    private S3FileUploaderService s3FileUploader;

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

        Optional<Category> categoryOptional = categoryRepository.findById(data.categoryId());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("category not found with ID: " + data.categoryId());
        }
        Category category = categoryOptional.get();

        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setImageUrl(imgUrl);
        newProduct.setPrice(data.price());
        newProduct.setStore(store);
        newProduct.setStatus(data.status());
        newProduct.setProductType(data.productType());
        newProduct.setCategory(category);
        newProduct.setAccompaniments(data.accompaniments());

        productRepository.save(newProduct);
        return newProduct;
    }

    public List<ProductResponseDTO> getProducts(UUID storeId) {
        List<Product> products = productRepository.findByStoreId(storeId);
        return products.stream().map(product -> new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getImageUrl(),
                product.getPrice(),
                product.getStore().getId(),
                product.getStatus(),
                product.getProductType(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getAccompaniments()
        )).toList();
    }

    public Product updateProduct(UUID id , ProductUpdateDTO data) {

        Optional<Product> product = productRepository.findById(id);

        Optional<Category> categoryOptional = categoryRepository.findById(data.categoryId());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("category not found with ID: " + data.categoryId());
        }
        Category category = categoryOptional.get();

        if (product.isPresent()) {
            Product productAlreadyExists = product.get();

            productAlreadyExists.setName(data.name());
            productAlreadyExists.setPrice(data.price());
            productAlreadyExists.setStatus(data.status());
            productAlreadyExists.setProductType(data.productType());
            productAlreadyExists.setCategory(category);
            productAlreadyExists.setAccompaniments(data.accompaniments());

            return productRepository.save(productAlreadyExists);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }

}
