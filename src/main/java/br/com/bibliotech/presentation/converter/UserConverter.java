package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.presentation.dto.UserDTO;
import br.com.bibliotech.presentation.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserConverter {

    private final AddressConverter addressConverter;

    @Autowired
    public UserConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public User fromDTO(UserDTO userDTO) {
        Address address = addressConverter.fromDTO(userDTO.addressDTO());
        return new User(userDTO.email(), userDTO.fullName(), userDTO.birthdate(), address);
    }

    public UserResponse fromModel(User user) {
        String address = Optional.ofNullable(user.getAddress()).map(Object::toString).orElse(null);

        return new UserResponse(user.getEmail(), user.getFullName(), user.getBirthdate(), address, user.isBlocked(),
                user.isDeleted());
    }

    public List<UserResponse> fromModelList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(fromModel(user)));

        return userResponses;
    }

    public User fromDTO(Long id, UserDTO userDTO) {
        Address address = addressConverter.fromDTO(userDTO.addressDTO());
        return new User(id, userDTO.email(), userDTO.fullName(), userDTO.birthdate(), address);
    }
}
