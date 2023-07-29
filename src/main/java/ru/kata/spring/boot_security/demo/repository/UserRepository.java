package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    void deleteById(Long id);

    Optional<User> findById(Long id);

    List<User> getAll();

    Optional<User> findByEmail(String email);
}
