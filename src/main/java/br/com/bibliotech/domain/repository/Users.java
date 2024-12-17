package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.User;

import java.util.List;

public interface Users {

    void save(User user);
    void update(User user);
    void delete(User user);
    User findById(Long id);
    List<User> findAll();
    User findByEmail(String email);
}
