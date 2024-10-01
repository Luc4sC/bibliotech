package br.com.bibliotech.responses;

public record StudentResponse(String rm, String fullName, String cellPhone, boolean blocked, AddressResponse addressResponse) {
}
