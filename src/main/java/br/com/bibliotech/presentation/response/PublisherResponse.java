package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record PublisherResponse(String name, String tradeName, LocalDate foundationDate, String address, boolean isDeleted) {
}
