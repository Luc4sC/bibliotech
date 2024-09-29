package br.com.bibliotech.entities;

import br.com.bibliotech.dtos.AddressDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
public class Address {

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String cep;

    public Address (AddressDTO addressDTO){
        this.street = addressDTO.street();
        this.number = addressDTO.number();
        this.neighborhood = addressDTO.neighborhood();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.cep = addressDTO.cep();
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number=" + number +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
