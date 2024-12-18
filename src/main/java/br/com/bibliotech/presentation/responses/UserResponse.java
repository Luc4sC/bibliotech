package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record UserResponse(String email, String fullName, LocalDate birthdate, String address, boolean isBlocked,
                           boolean isDeleted) {
}
