package com.budget.budget2.service;

import com.budget.budget2.entity.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(long id);

    void delete(long id);

}
