package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record UserResponse(String email, String fullName, LocalDate birthdate, String address, boolean isBlocked,
                           boolean isDeleted) {
}
