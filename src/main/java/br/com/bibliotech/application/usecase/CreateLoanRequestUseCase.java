package br.com.bibliotech.application.usecase;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.BookLoanRequest;
import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreateLoanRequestUseCase {

    private final LoanRequestService loanRequestService;
    private final UserService userService;
    private final BookService bookService;
    private final BookRequestService bookRequestService;

    @Autowired
    public CreateLoanRequestUseCase(LoanRequestService loanRequestService, UserService userService, BookService bookService,
                                    BookRequestService bookRequestService, LoanService loanService) {
        this.loanRequestService = loanRequestService;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRequestService = bookRequestService;
    }

    public void createRequest(String userEmail, List<Long> booksIds) {
        LoanRequest loanRequest = new LoanRequest(userService.findByEmail(userEmail));
        loanRequestService.save(loanRequest);

        createBookRequests(booksIds, loanRequest);
    }

    private void createBookRequests(List<Long> booksIds, LoanRequest loanRequest) {
        booksIds.forEach(bookId -> {
            Book book = bookService.findById(bookId);
            bookRequestService.save(new BookLoanRequest(book, loanRequest));
        });
    }

}
