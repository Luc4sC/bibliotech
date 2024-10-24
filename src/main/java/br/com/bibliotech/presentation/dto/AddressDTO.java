package br.com.bibliotech.presentation.dto;

public record AddressDTO(String street, int number, String neighborhood, String city, String state, String cep) {
}
