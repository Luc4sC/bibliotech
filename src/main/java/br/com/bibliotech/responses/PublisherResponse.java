package br.com.bibliotech.responses;

import java.time.LocalDate;
import java.util.List;

public record PublisherResponse(String tradeName, String name, LocalDate foundationDate, AddressResponse address) {
}
