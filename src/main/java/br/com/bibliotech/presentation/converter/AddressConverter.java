package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.presentation.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address fromDTO(AddressDTO addressDTO) {
        return new Address(addressDTO.street(), addressDTO.number(), addressDTO.neighborhood(),
                addressDTO.city(), addressDTO.state(), addressDTO.cep());
    }

}
