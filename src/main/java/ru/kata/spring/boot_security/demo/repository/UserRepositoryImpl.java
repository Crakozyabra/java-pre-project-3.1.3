package ru.kata.spring.boot_security.demo.repository;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (Objects.isNull(user.getId())) {
            em.persist(user);
            return user;
        }
        return Objects.isNull(findById(user.getId())) ? null : em.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        em.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public Optional<User> findById(Long id) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getResultStream().findAny();
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles", User.class).getResultList();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream().findAny();
    }
}