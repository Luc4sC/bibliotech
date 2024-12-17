package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.presentation.dto.UserDTO;
import br.com.bibliotech.presentation.responses.AddressResponse;
import br.com.bibliotech.presentation.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    private final AddressConverter addressConverter;

    @Autowired
    public UserConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public User fromDTO(UserDTO userDTO) {
        Address address = addressConverter.fromDto(userDTO.addressDTO());
        return new User(userDTO.email(), userDTO.fullName(), userDTO.birthdate(), address);
    }

    public UserResponse fromModel(User user) {
        AddressResponse addressResponse = addressConverter.fromModel(user.getAddress());
        return new UserResponse(user.getEmail(), user.getFullName(), user.getBirthdate(), addressResponse,
                user.isBlocked(), user.isDeleted());
    }

    public List<UserResponse> frommodelList(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(fromModel(user)));

        return userResponses;
    }

    public User fromDTO(Long id, UserDTO userDTO) {
        Address address = addressConverter.fromDto(userDTO.addressDTO());
        return new User(id, userDTO.email(), userDTO.fullName(), userDTO.birthdate(), address);
    }
}
