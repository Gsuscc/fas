package com.codecool.fas.repository;

import com.codecool.fas.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUsername(String username);
    boolean existsUserInfoByEmail(String email);
    boolean existsUserInfoByUsername(String username);
}
