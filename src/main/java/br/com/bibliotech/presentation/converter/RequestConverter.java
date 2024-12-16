package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.LoanRequest;
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

    public RequestResponse fromModel(LoanRequest loanRequest) {
        UserResponse userResponse = userConverter.fromModel(loanRequest.getUser());
        List<BookResponse> bookResponses = bookConverter.fromModelList(loanRequest.getBooks());

        return new RequestResponse(userResponse, bookResponses, loanRequest.getRequestDate(), loanRequest.getStatus().getName());
    }

    public List<RequestResponse> fromModelList(List<LoanRequest> loanRequests) {
        List<RequestResponse> requestResponses = new ArrayList<>();

        loanRequests.forEach(request -> requestResponses.add(fromModel(request)));
        return requestResponses;
    }

}
