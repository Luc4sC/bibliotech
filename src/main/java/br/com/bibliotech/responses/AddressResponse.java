package br.com.bibliotech.responses;

import br.com.bibliotech.entities.Address;

public record AddressResponse(String street, int number, String neighborhood, String city, String state, String cep) {

    public static AddressResponse converter(Address address){
        return new AddressResponse(address.getStreet(), address.getNumber(),address.getNeighborhood(), address.getCity(),
                address.getState(), address.getCep());
    }

}
