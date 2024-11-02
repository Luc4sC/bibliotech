package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.dto.PublisherDTO;
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
        return new PublisherResponse(publisher.getName(), publisher.getTradeName(), publisher.getFoundationDate(),
                addressConverter.fromModel(publisher.getAddress()));
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
