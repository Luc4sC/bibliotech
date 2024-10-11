package br.com.bibliotech.presentation.response;

public record StudentResponse(String rm, String fullName, String cellPhone, boolean blocked, AddressResponse addressResponse) {
}
