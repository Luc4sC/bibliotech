package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.repository.Users;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class UsersImpl implements Users {

    private final UserRepository userRepository;

    @Autowired
    UsersImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("User with email: " + user.getEmail() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void update(User user, User userUpdate) {
        try {
            user.update(userUpdate);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("User with email: " + user.getEmail() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void delete(User user) {
        user.delete();
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new NotFoundException("User with id: " + id + " not found!");

        return optionalUser.get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new NotFoundException("User with email: " + email + " not found!");

        return optionalUser.get();
    }
}
