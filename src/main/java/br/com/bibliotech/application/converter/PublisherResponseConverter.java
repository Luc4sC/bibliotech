package br.com.bibliotech.application.converter;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.presentation.response.AddressResponse;
import br.com.bibliotech.presentation.response.PublisherResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class PublisherResponseConverter implements Converter<PublisherResponse, Publisher>{

    public PublisherResponse convert(Publisher publisher){
        AddressResponseConverter addressResponseConverter = new AddressResponseConverter();
        AddressResponse addressResponse = addressResponseConverter.convert(publisher.getAddress());

        return new PublisherResponse(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(),
                addressResponse);
    }

    @Override
    public List<PublisherResponse> convertEach(List<Publisher> publishers){
        List<PublisherResponse> publisherResponses = new ArrayList<>();

        publishers.forEach(category -> {
            PublisherResponse categoryResponse = convert(category);
            publisherResponses.add(categoryResponse);
        });

        return publisherResponses;
    }

}
