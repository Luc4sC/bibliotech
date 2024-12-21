package br.com.bibliotech.application.usecase;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.exception.CannotBeAcceptedException;
import br.com.bibliotech.domain.exception.CannotBeBorrowedException;
import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.service.BookService;
import br.com.bibliotech.domain.service.LoanRequestService;
import br.com.bibliotech.domain.service.LoanService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class AcceptRequestUseCase {

    private final LoanRequestService loanRequestService;
    private final LoanService loanService;
    private final BookService bookService;

    @Autowired
    public AcceptRequestUseCase(LoanRequestService loanRequestService, LoanService loanService, BookService bookService) {
        this.loanRequestService = loanRequestService;
        this.loanService = loanService;
        this.bookService = bookService;
    }

    @Transactional
    public void accept(Long requestId, LocalDate endDate) {
        LoanRequest loanRequest = loanRequestService.findById(requestId);
        List<Book> books = loanRequest.getBooks();

        try {
            acceptLoanRequest(loanRequest);
            createLoan(endDate, loanRequest);
            borrowBooks(books);
        } catch (CannotBeAcceptedException | CannotBeBorrowedException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        log.info("Loan request: " + loanRequest + " accepted");
        log.info("Books: " + books + " borrowed");
    }

    private void acceptLoanRequest(LoanRequest loanRequest) {
        loanRequest.accept();
        loanRequestService.update(loanRequest);
    }

    private void createLoan(LocalDate endDate, LoanRequest loanRequest) {
        Loan loan = new Loan(endDate, loanRequest);
        loanService.save(loan);
    }

    private void borrowBooks(List<Book> books) {
        books.forEach(book -> {
            book.borrow();
            bookService.update(book);
        });
    }

}
