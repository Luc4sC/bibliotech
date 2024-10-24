package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.responses.PublisherResponse;

import java.util.ArrayList;
import java.util.List;

public class PublisherConverter implements GenericConverter<Publisher, PublisherDTO, PublisherResponse> {

    private final AddressConverter addressConverter;

    public PublisherConverter() {
        this.addressConverter = new AddressConverter();
    }

    @Override
    public Publisher fromDto(PublisherDTO dto) {
        return new Publisher(dto.tradeName(), dto.name(), dto.foundationDate(), addressConverter.fromDto(dto.address()));
    }

    @Override
    public PublisherResponse fromModel(Publisher model) {
        return new PublisherResponse(model.getName(), model.getTradeName(), model.getFoundationDate(),
                addressConverter.fromModel(model.getAddress()));
    }

    @Override
    public List<PublisherResponse> fromModelList(List<Publisher> models) {
        List<PublisherResponse> publisherResponses = new ArrayList<>();
        models.forEach(model -> {
            PublisherResponse publisherResponse = fromModel(model);
            publisherResponses.add(publisherResponse);
        });

        return publisherResponses;
    }
}
