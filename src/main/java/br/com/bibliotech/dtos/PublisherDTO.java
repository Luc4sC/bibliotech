package br.com.bibliotech.dtos;

import br.com.bibliotech.entities.Publisher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PublisherDTO(@NotEmpty @Size(min = 5, max = 50) String tradeName,
                           @NotEmpty @Size(min = 5, max = 50) String name,
                           @NotNull @PastOrPresent LocalDate foundationDate,
                           @NotNull AddressDTO addressDTO) {

    public PublisherDTO(Publisher publisher){
        this(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(), AddressDTO.converter(publisher.getAddress()));
    }

    public static PublisherDTO converter(Publisher publisher){
        return new PublisherDTO(publisher);
    }
}
