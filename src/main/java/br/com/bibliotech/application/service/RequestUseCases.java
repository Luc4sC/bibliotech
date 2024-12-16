package br.com.bibliotech.application.service;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.BookRequest;
import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.model.LoanRequest;
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
        LoanRequest loanRequest = new LoanRequest(userService.findById(userId));
        requestService.save(loanRequest);
        createBookRequests(booksIds, loanRequest);
    }

    private void createBookRequests(List<Long> booksIds, LoanRequest loanRequest) {
        booksIds.forEach(bookId -> {
            Book book = bookService.findById(bookId);
            bookRequestService.save(new BookRequest(book, loanRequest));
        });
    }

    public void accept(Long requestId, LocalDate endDate) {
        LoanRequest loanRequest = requestService.findById(requestId);
        acceptRequest(loanRequest);
        createLoan(endDate, loanRequest);
    }

    private void acceptRequest(LoanRequest loanRequest) {
        loanRequest.accept();
        requestService.update(loanRequest);
    }

    private void createLoan(LocalDate endDate, LoanRequest loanRequest) {
        Loan loan = new Loan(endDate, loanRequest);
        loanService.save(loan);
    }

    public void reject(Long requestId) {
        LoanRequest loanRequest = requestService.findById(requestId);
        rejectRequest(loanRequest);
    }

    private void rejectRequest(LoanRequest loanRequest) {
        loanRequest.reject();
        requestService.update(loanRequest);
    }

}
