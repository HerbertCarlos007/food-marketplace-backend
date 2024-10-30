package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.cart.Cart;
import com.example.food_marketplace.dto.CartRequestDTO;
import com.example.food_marketplace.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart addToCart(CartRequestDTO data) {
        Optional<Cart> cartAlreadyExists = cartRepository.findByProductIdAndUserIdAndStoreId(data.productId(), data.userId(), data.storeId());

        if (cartAlreadyExists.isPresent()) {
            Cart cart = cartAlreadyExists.get();
            cart.setQuantity(cart.getQuantity() + 1);
            return cartRepository.save(cart);
        } else {
            Cart newCart = new Cart();
            newCart.setProductId(data.productId());
            newCart.setUserId(data.userId());
            newCart.setStoreId(data.storeId());
            newCart.setQuantity(1);
            return cartRepository.save(newCart);
        }
    }
}
