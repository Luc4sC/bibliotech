package br.com.bibliotech.presentation.responses;

import java.time.LocalDate;

public record PublisherResponse(String name, String tradeName, LocalDate foundationDate, AddressResponse address) {
}
