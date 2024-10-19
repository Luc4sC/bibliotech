package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.PublisherDTO;
import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.response.AddressResponse;
import br.com.bibliotech.presentation.response.PublisherResponse;

import java.util.ArrayList;
import java.util.List;

public class PublisherConverter implements GenericConverter<Publisher, PublisherDTO, PublisherResponse>{

    private final AddressConverter addressConverter = new AddressConverter();

    @Override
    public Publisher modelFromDTO(PublisherDTO publisherDTO) {
        Address address = addressConverter.modelFromDTO(publisherDTO.addressDTO());
        return new Publisher(publisherDTO.tradeName(), publisherDTO.name(), publisherDTO.foundationDate(), address);
    }

    @Override
    public PublisherResponse responseFromModel(Publisher publisher) {
        AddressResponse addressResponse = addressConverter.responseFromModel(publisher.getAddress());
        return new PublisherResponse(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(),
                addressResponse);
    }

    @Override
    public List<PublisherResponse> responseListFromModelList(List<Publisher> publishers) {
        List<PublisherResponse> publisherResponses = new ArrayList<>();

        publishers.forEach(publisher -> {
            PublisherResponse publisherResponse = responseFromModel(publisher);
            publisherResponses.add(publisherResponse);
        });

        return publisherResponses;
    }
}
