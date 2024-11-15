package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.service.UserService;
import br.com.bibliotech.presentation.converter.UserConverter;
import br.com.bibliotech.presentation.dto.UserDTO;
import br.com.bibliotech.presentation.responses.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/user")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.userConverter = new UserConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserDTO userDTO) {
        User user = userConverter.fromDTO(userDTO);
        userService.save(user);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
        User userUpdate = userConverter.fromDTO(userDTO);
        userService.update(id, userUpdate);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return userConverter.fromModel(user);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAll() {
        List<User> users = userService.findAll();
        return userConverter.frommodelList(users);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return userConverter.fromModel(user);
    }

}
