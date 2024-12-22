package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.service.UserService;
import br.com.bibliotech.presentation.converter.UserConverter;
import br.com.bibliotech.presentation.dto.UserDTO;
import br.com.bibliotech.presentation.response.UserResponse;
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
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserDTO userDTO) {
        User user = userConverter.fromDTO(userDTO);
        userService.save(user);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id) {
        User user = userConverter.fromDTO(id, userDTO);
        userService.update(user);
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
        return userConverter.fromModelList(users);
    }

    @GetMapping(path = "/{email}",produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return userConverter.fromModel(user);
    }

    @PatchMapping(path = "/{id}/block", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void block(@PathVariable Long id) {
        userService.block(id);
    }

    @PatchMapping(path = "/{id}/unblock", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unblock(@PathVariable Long id) {
        userService.unblock(id);
    }

}
