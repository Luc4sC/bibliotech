package br.com.bibliotech.responses;

import java.util.List;

public record StudentResponse(String rm, String fullName, String cellPhone, boolean blocked, AddressResponse addressResponse, List<LoanResponse> loans) {
}
