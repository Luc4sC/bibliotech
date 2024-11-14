package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record UserResponse(String email, String fullName, LocalDate birthdate, AddressResponse addressResponse,
                           boolean isBlocked, boolean isDeleted) {
}
