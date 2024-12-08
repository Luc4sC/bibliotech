package br.com.bibliotech.application.service;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.BookRequest;
import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RequestUseCases {

    private final RequestService requestService;
    private final UserService userService;
    private final BookService bookService;
    private final BookRequestService bookRequestService;
    private final LoanService loanService;

    @Autowired
    public RequestUseCases(RequestService requestService, UserService userService, BookService bookService,
                           BookRequestService bookRequestService, LoanService loanService) {
        this.requestService = requestService;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRequestService = bookRequestService;
        this.loanService = loanService;
    }

    public void createRequest(Long userId, List<Long> booksIds) {
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

    public void accept(Long requestId, LocalDate endDate) {
        Request request = requestService.findById(requestId);
        acceptRequest(request);
        createLoan(endDate, request);
    }

    private void acceptRequest(Request request) {
        if (!request.isPending())
            throw new BadRequestException("Request with status: " + request.getStatus().getName() + " cannot be accept");

        request.accept();
        requestService.update(request);
    }

    private void createLoan(LocalDate endDate, Request request) {
        Loan loan = new Loan(endDate, request);
        loanService.save(loan);
    }

    public void reject(Long requestId) {
        Request request = requestService.findById(requestId);
        rejectRequest(request);
    }

    private void rejectRequest(Request request) {
        if (!request.isPending())
            throw new BadRequestException("Request with status: " + request.getStatus().getName() + " cannot be reject");

        request.reject();
        requestService.update(request);
    }

}
