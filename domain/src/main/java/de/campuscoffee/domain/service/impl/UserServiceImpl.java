package de.campuscoffee.domain.service.impl;

import de.campuscoffee.domain.model.User;
import de.campuscoffee.domain.service.UserService;
import de.campuscoffee.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementierung des UserService.
 *
 * WICHTIG: Diese Implementierung erwartet ein bestehendes Repository-Bean UserRepository
 * mit klassischen Methoden: findAll(), findById(Long), findByLogin(String), save(User), existsById(Long), deleteById(Long).
 *
 * Passe den Namen/Import an, falls dein Projekt ein anderes Repository-Interface verwendet.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // erwartet vorhanden

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User create(User user) {
        // createdAt/updatedAt sollten bereits gesetzt sein (User.createNew)
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(Long id, User user) {
        return userRepository.findById(id).map(existing -> {
            existing.copyWithUpdateFrom(user);
            return userRepository.save(existing);
        });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}