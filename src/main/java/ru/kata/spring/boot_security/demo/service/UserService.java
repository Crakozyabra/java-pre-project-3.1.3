package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    void delete(Long id);

    User findById(Long id);

    List<User> getAll();

    User findByUsername(String username);
}
