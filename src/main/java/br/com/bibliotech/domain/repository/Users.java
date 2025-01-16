package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Users {

    void save(User user);
    void update(User user);
    void delete(User user);
    User findById(Long id);
    Page<User> findAll(Pageable pageable);
    User findByEmail(String email);
}
