package br.com.bibliotech.application.usecase;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.exception.AllBooksInStockException;
import br.com.bibliotech.domain.exception.LoanAlreadyFinishedException;
import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.service.BookService;
import br.com.bibliotech.domain.service.LoanService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FinishLoanUseCase {

    private final LoanService loanService;
    private final BookService bookService;

    @Autowired
    public FinishLoanUseCase(LoanService loanService, BookService bookService) {
        this.loanService = loanService;
        this.bookService = bookService;
    }

    @Transactional
    public void finish(Long loanId) {
        Loan loan = loanService.findById(loanId);
        List<Book> books = loan.getBooks();

        try {
            loan.finish();
            loanService.update(loan);
            giveBackBooks(books);
        } catch (LoanAlreadyFinishedException | AllBooksInStockException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        log.info("Loan finished: " + loan);
        log.info("Books: " + books + " returned");
    }

    private void giveBackBooks(List<Book> books) {
        books.forEach(book -> {
            book.giveBack();
            bookService.update(book);
        });
    }

}
