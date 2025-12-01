package de.campuscoffee.domain.service;

import de.campuscoffee.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    User create(User user);
    Optional<User> update(Long id, User user);
    void delete(Long id);
}