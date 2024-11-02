package br.com.bibliotech.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(nullable = false)
    private final String street;

    @Column(nullable = false)
    private final int number;

    @Column(nullable = false)
    private final String neighborhood;

    @Column(nullable = false)
    private final String city;

    @Column(nullable = false)
    private final String state;

    @Column(nullable = false)
    private final String cep;

    public Address (String street, int number, String neighborhood, String city, String state, String cep){
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s %s", street, number, neighborhood, city, state, cep);
    }

}
