package br.com.bibliotech.presentation.response;


public record AddressResponse(String street, int number, String neighborhood, String city, String state, String cep) {
}
