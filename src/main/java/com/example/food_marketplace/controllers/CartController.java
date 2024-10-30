package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.cart.Cart;
import com.example.food_marketplace.dto.CartRequestDTO;
import com.example.food_marketplace.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping()
    public ResponseEntity<Cart> addToCart(@RequestBody CartRequestDTO data) {
        Cart cart = cartService.addToCart(data);
        return ResponseEntity.ok(cart);
    }
}
