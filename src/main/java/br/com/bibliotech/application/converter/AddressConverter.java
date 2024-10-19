package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.AddressDTO;
import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.presentation.response.AddressResponse;

import java.util.ArrayList;
import java.util.List;

public class AddressConverter implements GenericConverter<Address, AddressDTO, AddressResponse>{
    @Override
    public Address modelFromDTO(AddressDTO addressDTO) {
        return new Address(addressDTO.street(), addressDTO.number(), addressDTO.neighborhood(),
                addressDTO.city(), addressDTO.state(), addressDTO.cep());
    }

    @Override
    public AddressResponse responseFromModel(Address address) {
        return new AddressResponse(address.getStreet(), address.getNumber(), address.getNeighborhood(),
                address.getCity(), address.getState(), address.getCep());
    }

    @Override
    public List<AddressResponse> responseListFromModelList(List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();

        addresses.forEach(address -> {
            AddressResponse addressResponse = responseFromModel(address);
            addressResponses.add(addressResponse);
        });

        return addressResponses;
    }
}
