package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.response.PublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PublisherConverter {

    private final AddressConverter addressConverter;

    @Autowired
    public PublisherConverter(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    public Publisher fromDTO(PublisherDTO publisherDTO) {
        return new Publisher(publisherDTO.tradeName(), publisherDTO.name(), publisherDTO.foundationDate(),
                addressConverter.fromDTO(publisherDTO.address()));
    }

    public PublisherResponse fromModel(Publisher publisher) {
        String address = Optional.ofNullable(publisher.getAddress()).map(Object::toString).orElse(null);

        return new PublisherResponse(publisher.getLegalName(), publisher.getTradeName(), publisher.getFoundationDate(),
                address, publisher.isDeleted());
    }

    public List<PublisherResponse> fromModelList(List<Publisher> publishers) {
        List<PublisherResponse> publisherResponses = new ArrayList<>();
        publishers.forEach(publisher -> publisherResponses.add(fromModel(publisher)));

        return publisherResponses;
    }

    public Publisher fromDTO(Long id, PublisherDTO publisherDTO) {
        return new Publisher(id, publisherDTO.tradeName(), publisherDTO.name(), publisherDTO.foundationDate(),
                addressConverter.fromDTO(publisherDTO.address()));
    }
}
