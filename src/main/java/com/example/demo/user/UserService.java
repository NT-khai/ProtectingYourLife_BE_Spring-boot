package com.example.demo.user;

import com.example.demo.user.dto.UserProfileDto;
import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();
    UserEntity getUserById(Long id);
    UserEntity getUserByEmail(String email);
    UserEntity updateUserRole(Long id, String role);
    void deleteUser(Long id);

    UserProfileEntity getUserProfile(Long userId);
    UserProfileEntity updateUserProfile(Long userId, UserProfileDto dto);
}
