package com.hecto.mylogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hecto.mylogin.entity.UserEntity;

// 사용자 데이터를 액세스
// 사용자 정보 가져오거나 저장
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUserName(String userName);

    public UserEntity findByUserEmail(String userEmail);
}
