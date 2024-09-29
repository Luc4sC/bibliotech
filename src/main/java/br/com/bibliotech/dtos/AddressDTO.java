package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Address;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(@NotEmpty @Size(min = 7, max = 50) String street, @Max(value = 9999) int number,
                         @NotEmpty @Size(min = 5, max = 50) String neighborhood,
                         @NotEmpty @Size(min = 5, max = 25) String city,
                         @NotEmpty @Size(min = 5, max = 20) String state,
                         @NotEmpty @Pattern(regexp = "^\\d{5}-\\d{3}$") String cep) {

    public AddressDTO(Address address){
        this(address.getStreet(), address.getNumber(), address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCep());
    }

    public static AddressDTO converter(Address address){
        return new AddressDTO(address);
    }
}
