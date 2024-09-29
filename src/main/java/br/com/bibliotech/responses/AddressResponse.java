package br.com.bibliotech.responses;


public record AddressResponse(String street, int number, String neighborhood, String city, String state, String cep) {
}
