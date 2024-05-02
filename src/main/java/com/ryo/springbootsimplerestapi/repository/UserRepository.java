package com.ryo.springbootsimplerestapi.repository;

import com.ryo.springbootsimplerestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}