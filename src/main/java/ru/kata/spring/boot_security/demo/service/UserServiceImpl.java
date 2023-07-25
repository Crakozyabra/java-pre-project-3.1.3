package ru.kata.spring.boot_security.demo.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private UserRepositoryImpl repository;

    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User save(User user) {
        user.setRoles(user.getRoles().stream().filter(role -> role.getId() != null).collect(Collectors.toList()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).map(UserServiceImpl::removeIdCryptoPrefix)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + id + "' was not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.getAll().stream().map(UserServiceImpl::removeIdCryptoPrefix).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' was not found"));
    }

    private static User removeIdCryptoPrefix(User user) {
        user.setPassword(user.getPassword().replaceAll("\\{.+}",""));
        return user;
    }
}