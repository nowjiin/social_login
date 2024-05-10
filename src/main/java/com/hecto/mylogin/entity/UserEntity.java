package com.hecto.mylogin.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fitnessuniv")
public class UserEntity {
    @Id
    // DB중 Auto Increment 로 설정한게 있으면 반드시 해줘야함.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    // 0으로 시작 할 수 도 있어서 String 으로
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "user_role", nullable = false)
    private String userRole = "ROLE_USER";

    @Column(name = "join_path")
    private String joinPath;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public UserEntity(
            String userName,
            String userEmail,
            String userRole,
            String joinPath,
            Timestamp createdAt) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRole = userRole != null ? userRole : "ROLE_USER";
        this.joinPath = joinPath;
        this.createdAt = createdAt;
    }
}
