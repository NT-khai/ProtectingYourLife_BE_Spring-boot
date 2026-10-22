package com.example.demo.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String hovaten;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(length = 255)
    private String matkhau;

    @Column(name = "google_id", unique = true, length = 255)
    private String googleId;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(length = 20)
    private String provider = "credentials";

    @Column(length = 20)
    private String role = "user";

    @Column(name = "email_verified")
    private Boolean emailVerified = false;

    @Column(name = "ngaytao")
    private LocalDateTime ngaytao;

    public UserEntity() {
    }

    @PrePersist
    protected void onCreate() {
        if (this.ngaytao == null) {
            this.ngaytao = LocalDateTime.now();
        }
        if (this.provider == null) {
            this.provider = "credentials";
        }
        if (this.role == null) {
            this.role = "user";
        }
        if (this.emailVerified == null) {
            this.emailVerified = false;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }
}
