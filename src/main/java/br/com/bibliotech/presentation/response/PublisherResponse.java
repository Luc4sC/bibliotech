package br.com.bibliotech.presentation.response;

import java.time.LocalDate;

public record PublisherResponse(String tradeName, String name, LocalDate foundationDate, AddressResponse address) {
}
