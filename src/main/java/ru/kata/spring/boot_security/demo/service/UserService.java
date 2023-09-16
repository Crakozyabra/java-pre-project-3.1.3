package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);

    void delete(Long id);

    UserDto findById(Long id);

    List<UserDto> getAll();

    User findByEmail(String email);
}
