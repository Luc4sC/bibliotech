package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.presentation.dto.AddressDTO;
import br.com.bibliotech.presentation.responses.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address fromDto(AddressDTO addressDTO) {
        return new Address(addressDTO.street(), addressDTO.number(), addressDTO.neighborhood(),
                addressDTO.city(), addressDTO.state(), addressDTO.cep());
    }

    public AddressResponse fromModel(Address address) {
        return new AddressResponse(address.getStreet(), address.getNumber(), address.getNeighborhood(), address.getCity(), address.getState(), address.getCep());
    }

}
