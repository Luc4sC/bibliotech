package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.responses.AddressResponse;
import br.com.bibliotech.presentation.responses.PublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherConverter {

    private final AddressConverter addressConverter;

    @Autowired
    public PublisherConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
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
        publishers.forEach(publisher -> publisherResponses.add(fromModel(publisher)));

        return publisherResponses;
    }
}
