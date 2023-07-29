package ru.kata.spring.boot_security.demo.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User save(User user) {
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
        return repository.findById(id)
                /*.map(UserServiceImpl::removeIdCryptoPrefix)*/
                .orElseThrow(() -> new UsernameNotFoundException("User '" + id + "' was not found"));
    }

    @Override
    public List<User> getAll() {
        return repository.getAll().stream()
                /*.map(UserServiceImpl::removeIdCryptoPrefix)*/
                .collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + email + "' was not found"));
    }

    private static User removeIdCryptoPrefix(User user) {
        user.setPassword(user.getPassword().replaceAll("\\{.+}",""));
        return user;
    }
}