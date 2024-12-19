package br.com.bibliotech.application.service;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.exception.CannotBeBorrowedException;
import br.com.bibliotech.domain.exception.CannotBeAcceptedException;
import br.com.bibliotech.domain.exception.CannotBeRejectedException;
import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.BookLoanRequest;
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

    private final LoanRequestService loanRequestService;
    private final UserService userService;
    private final BookService bookService;
    private final BookRequestService bookRequestService;
    private final LoanService loanService;

    @Autowired
    public RequestUseCases(LoanRequestService loanRequestService, UserService userService, BookService bookService,
                           BookRequestService bookRequestService, LoanService loanService) {
        this.loanRequestService = loanRequestService;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRequestService = bookRequestService;
        this.loanService = loanService;
    }

    public void createRequest(Long userId, List<Long> booksIds) {
        LoanRequest loanRequest = new LoanRequest(userService.findById(userId));
        loanRequestService.save(loanRequest);
        createBookRequests(booksIds, loanRequest);
    }

    private void createBookRequests(List<Long> booksIds, LoanRequest loanRequest) {
        booksIds.forEach(bookId -> {
            Book book = bookService.findById(bookId);
            bookRequestService.save(new BookLoanRequest(book, loanRequest));
        });
    }

    public void accept(Long requestId, LocalDate endDate) {
        LoanRequest loanRequest = loanRequestService.findById(requestId);
        borrowBooks(loanRequest.getBooks());
        acceptRequest(loanRequest);
        createLoan(endDate, loanRequest);
    }

    private void borrowBooks(List<Book> books) {
        books.forEach(book -> {
            try {
                book.borrow();
                bookService.update(book);
            } catch (CannotBeBorrowedException exception) {
                throw new BadRequestException(exception.getMessage());
            }
        });
    }

    private void acceptRequest(LoanRequest loanRequest) {
        try {
            loanRequest.accept();
            loanRequestService.update(loanRequest);
        } catch (CannotBeAcceptedException cannotBeAcceptedException) {
            throw new BadRequestException(cannotBeAcceptedException.getMessage());
        }
    }

    private void createLoan(LocalDate endDate, LoanRequest loanRequest) {
        Loan loan = new Loan(endDate, loanRequest);
        loanService.save(loan);
    }

    public void reject(Long requestId) {
        LoanRequest loanRequest = loanRequestService.findById(requestId);
        rejectRequest(loanRequest);
    }

    private void rejectRequest(LoanRequest loanRequest) {
        try {
            loanRequest.reject();
            loanRequestService.update(loanRequest);
        } catch (CannotBeRejectedException cannotBeRejectedException) {
            throw new BadRequestException(cannotBeRejectedException.getMessage());
        }

    }

}
