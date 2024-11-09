package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.responses.AddressResponse;
import br.com.bibliotech.presentation.responses.PublisherResponse;

import java.util.ArrayList;
import java.util.List;

public class PublisherConverter {

    private final AddressConverter addressConverter;

    public PublisherConverter() {
        this.addressConverter = new AddressConverter();
    }

    public Publisher fromDto(PublisherDTO publisherDTO) {
        return new Publisher(publisherDTO.tradeName(), publisherDTO.name(), publisherDTO.foundationDate(),
                addressConverter.fromDto(publisherDTO.address()));
    }

    public PublisherResponse fromModel(Publisher publisher) {
        AddressResponse addressResponse = publisher.getAddress() != null ?
                addressConverter.fromModel(publisher.getAddress()) : null;

        return new PublisherResponse(publisher.getLegalName(), publisher.getTradeName(), publisher.getFoundationDate(),
                addressResponse, publisher.isDeleted());
    }

    public List<PublisherResponse> fromModelList(List<Publisher> publishers) {
        List<PublisherResponse> publisherResponses = new ArrayList<>();
        publishers.forEach(model -> {
            PublisherResponse publisherResponse = fromModel(model);
            publisherResponses.add(publisherResponse);
        });

        return publisherResponses;
    }
}
