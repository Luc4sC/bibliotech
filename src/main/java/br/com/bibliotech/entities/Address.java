package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Setter
public class Address {

    private String street;

    private int number;

    private String neighborhood;

    private String city;

    private String state;

    private String cep;

    public Address (AddressDTO addressDTO){
        this.street = addressDTO.street();
        this.number = addressDTO.number();
        this.neighborhood = addressDTO.neighborhood();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.cep = addressDTO.cep();
    }
}
