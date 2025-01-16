package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.repository.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final Users users;

    @Autowired
    public UserService(Users users) {
        this.users = users;
    }

    public void save(User user) {
        users.save(user);
        log.info("User created: " + user);
    }

    public void update(User user) {
        users.update(user);
        log.info("User updated: " + user);
    }

    public void delete(Long id) {
        User user = users.findById(id);
        users.delete(user);
        log.info("User deleted: " + user);
    }

    public User findById(Long id) {
        return users.findById(id);
    }

    public Page<User> findAll(Pageable pageable) {
        return users.findAll(pageable);
    }

    public User findByEmail(String email) {
        return users.findByEmail(email);
    }

    public void block(Long id) {
        User user = users.findById(id);
        user.block();
        user.update(user);
        log.info("User blocked: " + user);
    }

    public void unblock(Long id) {
        User user = users.findById(id);
        user.unblock();
        user.update(user);
        log.info("User unblocked: " + user);
    }

}
