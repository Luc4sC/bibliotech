package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Publisher;
import br.com.bibliotech.responses.AddressResponse;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.PublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PublisherResponseConverter implements Converter<PublisherResponse, Publisher>{

    @Autowired
    private AddressResponseConverter addressResponseConverter;

    @Autowired
    private BookResponseConverter bookResponseConverter;

    public PublisherResponse convert(Publisher publisher){
        List<BookResponse> bookResponses = bookResponseConverter.convertEach(publisher.getBooks());
        AddressResponse addressResponse = addressResponseConverter.convert(publisher.getAddress());

        return new PublisherResponse(publisher.getTradeName(), publisher.getName(), publisher.getFoundationDate(),
                addressResponse, bookResponses);
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
