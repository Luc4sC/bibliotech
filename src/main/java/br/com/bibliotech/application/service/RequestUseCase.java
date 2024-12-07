package br.com.bibliotech.application.service;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.BookRequest;
import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.service.BookRequestService;
import br.com.bibliotech.domain.service.BookService;
import br.com.bibliotech.domain.service.RequestService;
import br.com.bibliotech.domain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RequestUseCase {

    private final RequestService requestService;
    private final UserService userService;
    private final BookService bookService;
    private final BookRequestService bookRequestService;

    @Autowired
    public RequestUseCase(RequestService requestService, UserService userService, BookService bookService,
                          BookRequestService bookRequestService) {
        this.requestService = requestService;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRequestService = bookRequestService;
    }

    public void request(Long userId, List<Long> booksIds) {
        Request request = new Request(userService.findById(userId));
        requestService.save(request);
        createBookRequests(booksIds, request);
    }

    private void createBookRequests(List<Long> booksIds, Request request) {
        booksIds.forEach(bookId -> {
            Book book = bookService.findById(bookId);
            bookRequestService.save(new BookRequest(book, request));
        });
    }

}
