package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.product.Product;
import com.example.food_marketplace.dto.product.ProductRequestDTO;
import com.example.food_marketplace.dto.product.ProductResponseDTO;
import com.example.food_marketplace.dto.product.ProductUpdateDTO;
import com.example.food_marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Product> create(@RequestParam("name") String name,
                                          @RequestParam(value = "imageUrl", required = false) MultipartFile imageUrl,
                                          @RequestParam("price") double price,
                                          @RequestParam("storeId") UUID storeId,
                                          @RequestParam("inStock") String inStock,
                                          @RequestParam("productType") String productType,
                                          @RequestParam("categoryId") UUID categoryId,
                                          @RequestParam("accompaniments") String accompaniments){
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(name, imageUrl, price, storeId, inStock, productType, categoryId, accompaniments);
        Product newProduct = this.productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@PathVariable UUID storeId) {
        List<ProductResponseDTO> allProducts = productService.getProducts(storeId);
        return ResponseEntity.ok(allProducts);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("price") double price,
                                                 @RequestParam("inStock") String inStock,
                                                 @RequestParam("productType") String productType,
                                                 @RequestParam("categoryId") UUID categoryId,
                                                 @RequestParam("accompaniments") String accompaniments) {
        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO(name, price, inStock, productType, categoryId, accompaniments);
        Product product = productService.updateProduct(id, productUpdateDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{storeId}/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id, @PathVariable UUID storeId) {
       productService.deleteProduct(id, storeId);
       return ResponseEntity.noContent().build();
    }
}
