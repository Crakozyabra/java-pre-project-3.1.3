package ru.kata.spring.boot_security.demo.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.dto.UserMapper;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.toTo(repository.save(userMapper.toEntity(userDto)));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toTo(repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + id + "' was not found")));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.toToList(repository.getAll());
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + email + "' was not found"));
    }
}