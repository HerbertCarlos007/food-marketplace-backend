package com.example.food_marketplace.controllers;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.domain.user.User;
import com.example.food_marketplace.dto.user.LoginRequestDTO;
import com.example.food_marketplace.dto.user.RegisterRequestDTO;
import com.example.food_marketplace.dto.user.ResponseDTO;
import com.example.food_marketplace.dto.user.UpdateUserDTO;
import com.example.food_marketplace.infra.TokenService;
import com.example.food_marketplace.repositories.StoreRepository;
import com.example.food_marketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

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
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token, user.getRole(), user.getStatus()));
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
        newUser.setRole(body.role());
        newUser.setStatus(body.status());

        this.userRepository.save(newUser);

        String token = this.tokenService.generateToken(newUser);
        return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token, newUser.getRole(), newUser.getStatus()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateUserDTO body) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        User user = userOptional.get();
        user.setEmail(body.email());
        user.setRole(body.role());
        user.setName(body.name());
        user.setStatus(body.status());

        this.userRepository.save(user);

        String newToken = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new ResponseDTO(user.getName(), newToken, user.getRole(), user.getStatus()));
    }

}
