package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.domain.user.User;
import com.example.food_marketplace.dto.user.LoginRequestDTO;
import com.example.food_marketplace.dto.user.RegisterRequestDTO;
import com.example.food_marketplace.dto.user.ResponseDTO;
import com.example.food_marketplace.infra.TokenService;
import com.example.food_marketplace.repositories.StoreRepository;
import com.example.food_marketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> userExists = this.userRepository.findByEmail(body.email());
        if (userExists.isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        Optional<Store> storeOptional = this.storeRepository.findById(body.storeId());
        if (storeOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Loja não encontrada");
        }
        Store store = storeOptional.get();

        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setName(body.name());
        newUser.setStore(store);

        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
    }
}
