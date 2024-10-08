package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Address;
import br.com.bibliotech.responses.AddressResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AddressResponseConverter implements Converter<AddressResponse, Address>{

    @Override
    public AddressResponse convert(Address address){
        return new AddressResponse(address.getStreet(), address.getNumber(),address.getNeighborhood(), address.getCity(),
                address.getState(), address.getCep());
    }

    @Override
    public List<AddressResponse> convertEach(List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();

        addresses.forEach(address -> {
            AddressResponse addressResponse = convert(address);
            addressResponses.add(addressResponse);
        });

        return addressResponses;
    }
}
