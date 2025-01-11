package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.store.Store;
import com.example.food_marketplace.domain.user.User;
import com.example.food_marketplace.dto.user.LoginRequestDTO;
import com.example.food_marketplace.dto.user.RegisterRequestDTO;
import com.example.food_marketplace.dto.user.ResponseDTO;
import com.example.food_marketplace.dto.user.UserResponseDTO;
import com.example.food_marketplace.infra.TokenService;
import com.example.food_marketplace.repositories.StoreRepository;
import com.example.food_marketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    StoreRepository storeRepository;

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        )).toList();
    }

    public ResponseEntity<ResponseDTO> login(LoginRequestDTO body) {
        User user = this.userRepository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            ResponseDTO response = new ResponseDTO(user.getName(), token, user.getRole(), user.getStatus());
            return ResponseEntity.ok(response);
        }

        throw new BadCredentialsException("Invalid credentials");
    }

    public ResponseEntity<?> register(RegisterRequestDTO body) {
        Optional<User> userExists = this.userRepository.findByEmail(body.email());
        if (userExists.isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe!");
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

}
