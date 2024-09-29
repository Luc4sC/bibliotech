package br.com.bibliotech.responses;

import br.com.bibliotech.entities.Publisher;

import java.time.LocalDate;

public record PublisherResponse(String tradeName, String name, LocalDate foundationDate, AddressResponse address) {

    public static PublisherResponse converter(Publisher publisher){
        AddressResponse addressResponse = AddressResponse.converter(publisher.getAddress());

        return new PublisherResponse(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(),
                 addressResponse);
    }

}
