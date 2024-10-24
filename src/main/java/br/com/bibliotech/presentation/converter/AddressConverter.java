package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.presentation.dto.AddressDTO;
import br.com.bibliotech.presentation.responses.AddressResponse;

import java.util.ArrayList;
import java.util.List;

public class AddressConverter implements GenericConverter<Address, AddressDTO, AddressResponse> {


    @Override
    public Address fromDto(AddressDTO dto) {
        return new Address(dto.street(), dto.number(), dto.neighborhood(), dto.city(), dto.state(), dto.cep());
    }

    @Override
    public AddressResponse fromModel(Address model) {
        return new AddressResponse(model.toString());
    }

    @Override
    public List<AddressResponse> fromModelList(List<Address> models) {
        List<AddressResponse> addressResponses = new ArrayList<>();
        models.forEach(model -> {
            AddressResponse addressResponse = fromModel(model);
            addressResponses.add(addressResponse);
        });

        return addressResponses;
    }

}
