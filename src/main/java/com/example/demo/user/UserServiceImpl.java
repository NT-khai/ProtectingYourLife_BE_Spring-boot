package com.example.demo.user;

import com.example.demo.user.dto.UserProfileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            UserProfileRepository userProfileRepository
    ) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với id: " + id));
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với email: " + email));
    }

    @Override
    @Transactional
    public UserEntity updateUserRole(Long id, String role) {
        UserEntity user = getUserById(id);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userProfileRepository.findByUserId(id).ifPresent(userProfileRepository::delete);
        userRepository.deleteById(id);
    }

    @Override
    public UserProfileEntity getUserProfile(Long userId) {
        return userProfileRepository.findByUserId(userId).orElseGet(() -> {
            UserProfileEntity newProfile = new UserProfileEntity();
            newProfile.setUserId(userId);
            userRepository.findById(userId).ifPresentOrElse(user -> {
                newProfile.setHovaten(user.getHovaten());
                newProfile.setAvatar(user.getAvatar());
            }, () -> {
                newProfile.setHovaten("Người dùng");
            });
            return userProfileRepository.save(newProfile);
        });
    }

    @Override
    @Transactional
    public UserProfileEntity updateUserProfile(Long userId, UserProfileDto dto) {
        UserProfileEntity profile = userProfileRepository.findByUserId(userId).orElseGet(() -> {
            UserProfileEntity p = new UserProfileEntity();
            p.setUserId(userId);
            p.setHovaten(dto.getHovaten() != null && !dto.getHovaten().isBlank() ? dto.getHovaten() : "Người dùng");
            return p;
        });

        if (dto.getHovaten() != null && !dto.getHovaten().isBlank()) {
            profile.setHovaten(dto.getHovaten());
            userRepository.findById(userId).ifPresent(u -> {
                u.setHovaten(dto.getHovaten());
                if (dto.getAvatar() != null) {
                    u.setAvatar(dto.getAvatar());
                }
                userRepository.save(u);
            });
        }
        if (dto.getSoDienThoai() != null) {
            profile.setSoDienThoai(dto.getSoDienThoai());
        }
        if (dto.getDiaChi() != null) {
            profile.setDiaChi(dto.getDiaChi());
        }
        if (dto.getAvatar() != null) {
            profile.setAvatar(dto.getAvatar());
        }

        return userProfileRepository.save(profile);
    }
}
