package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Publisher;
import br.com.bibliotech.responses.AddressResponse;
import br.com.bibliotech.responses.PublisherResponse;
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
