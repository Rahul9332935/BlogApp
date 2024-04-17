package com.blog.service;

import java.util.List;

import com.blog.exceptions.UserNotFoundException;
import com.blog.model.User;

public interface UserService {

    List<User> getAllUser();

    User getUserById(Long userId) throws UserNotFoundException;

    String addUser(User user);

    User updateUser(Long userId, User userDetails) throws UserNotFoundException;

    void deleteUser(Long userId) throws UserNotFoundException;
}
