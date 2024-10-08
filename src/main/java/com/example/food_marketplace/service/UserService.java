package com.example.food_marketplace.service;

import com.example.food_marketplace.domain.user.User;
import com.example.food_marketplace.dto.user.UserResponseDTO;
import com.example.food_marketplace.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        )).toList();
    }
}
