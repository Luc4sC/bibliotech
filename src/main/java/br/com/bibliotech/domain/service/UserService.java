package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.repository.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void update(Long id, User userUpdate) {
        User user = users.findById(id);
        users.update(user, userUpdate);
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

    public List<User> findAll() {
        return users.findAll();
    }

    public User findByEmail(String email) {
        return users.findByEmail(email);
    }

}
