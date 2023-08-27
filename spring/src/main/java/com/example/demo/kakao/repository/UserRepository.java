package com.example.demo.kakao.repository;

import com.example.demo.kakao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
