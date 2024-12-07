package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.model.User;
import br.com.bibliotech.domain.service.*;
import br.com.bibliotech.presentation.dto.RequestDTO;
import br.com.bibliotech.presentation.responses.BookResponse;
import br.com.bibliotech.presentation.responses.RequestResponse;
import br.com.bibliotech.presentation.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RequestConverter {

    private final BookConverter bookConverter;
    private final UserConverter userConverter;
    private final UserService userService;

    public RequestConverter(UserService userService, AuthorService authorService, CategoryService categoryService,
                            GenreService genreService, PublisherService publisherService) {

        this.bookConverter = new BookConverter(authorService, categoryService, genreService, publisherService);
        this.userConverter = new UserConverter();
        this.userService = userService;
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
