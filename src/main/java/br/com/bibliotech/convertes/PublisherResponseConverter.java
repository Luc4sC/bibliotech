package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Publisher;
import br.com.bibliotech.responses.AddressResponse;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.PublisherResponse;

import java.util.ArrayList;
import java.util.List;

public class PublisherResponseConverter {

    public static PublisherResponse convert(Publisher publisher){
        List<BookResponse> bookResponses = BookResponseConverter.convertList(publisher.getBooks());
        AddressResponse addressResponse = AddressResponseConverter.convert(publisher.getAddress());

        return new PublisherResponse(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(),
                addressResponse, bookResponses);
    }

    public static List<PublisherResponse> convertList(List<Publisher> publishers){
        List<PublisherResponse> publisherResponses = new ArrayList<>();

        publishers.forEach(category -> {
            PublisherResponse categoryResponse = convert(category);
            publisherResponses.add(categoryResponse);
        });

        return publisherResponses;
    }

}
