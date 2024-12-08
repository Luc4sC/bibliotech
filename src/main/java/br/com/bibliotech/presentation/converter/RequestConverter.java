package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.presentation.responses.BookResponse;
import br.com.bibliotech.presentation.responses.RequestResponse;
import br.com.bibliotech.presentation.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestConverter {

    private final BookConverter bookConverter;
    private final UserConverter userConverter;

    @Autowired
    public RequestConverter(BookConverter bookConverter, UserConverter userConverter) {
        this.bookConverter = bookConverter;
        this.userConverter = userConverter;
    }

    public RequestResponse fromModel(Request request) {
        UserResponse userResponse = userConverter.fromModel(request.getUser());
        List<BookResponse> bookResponses = bookConverter.fromModelList(request.getBooks());

        return new RequestResponse(userResponse, bookResponses, request.getStatus().getName());
    }

    public List<RequestResponse> fromModelList(List<Request> requests) {
        List<RequestResponse> requestResponses = new ArrayList<>();

        requests.forEach(request -> requestResponses.add(fromModel(request)));
        return requestResponses;
    }

}
