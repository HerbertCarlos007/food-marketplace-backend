package com.example.food_marketplace.controllers;
import com.example.food_marketplace.dto.user.LoginRequestDTO;
import com.example.food_marketplace.dto.user.RegisterRequestDTO;
import com.example.food_marketplace.dto.user.ResponseDTO;
import com.example.food_marketplace.dto.user.UpdateUserDTO;
import com.example.food_marketplace.infra.TokenService;
import com.example.food_marketplace.repositories.StoreRepository;
import com.example.food_marketplace.repositories.UserRepository;
import com.example.food_marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
        return userService.login(body);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
        return userService.register(body);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateUserDTO body) {
        return userService.update(id, body);
    }

}
