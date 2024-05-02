package com.ryo.springbootsimplerestapi.service;

import com.ryo.springbootsimplerestapi.entity.User;
import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long userId);
}