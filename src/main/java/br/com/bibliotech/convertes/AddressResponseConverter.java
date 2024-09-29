package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Address;
import br.com.bibliotech.responses.AddressResponse;

public class AddressResponseConverter {

    public static AddressResponse convert(Address address){
        return new AddressResponse(address.getStreet(), address.getNumber(),address.getNeighborhood(), address.getCity(),
                address.getState(), address.getCep());
    }
}
