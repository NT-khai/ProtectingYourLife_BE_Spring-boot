package com.example.demo.user;

import com.example.demo.user.dto.UserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}/role")
    public UserEntity updateUserRole(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String role = body.getOrDefault("role", "user");
        return userService.updateUserRole(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("success", true, "message", "Đã xóa người dùng"));
    }

    @GetMapping("/profile/{userId}")
    public UserProfileEntity getUserProfile(@PathVariable Long userId) {
        return userService.getUserProfile(userId);
    }

    @PutMapping("/profile/{userId}")
    public UserProfileEntity updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfileDto dto
    ) {
        return userService.updateUserProfile(userId, dto);
    }
}
