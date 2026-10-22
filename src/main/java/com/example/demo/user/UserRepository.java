package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(
            String email
    );

    Optional<UserEntity> findByGoogleId(
            String googleId
    );

    List<UserEntity> findByRole(
            String role
    );

    List<UserEntity> findByEmailVerified(
            Boolean emailVerified
    );

    

    boolean existsByGoogleId(
            String googleId
    );
}