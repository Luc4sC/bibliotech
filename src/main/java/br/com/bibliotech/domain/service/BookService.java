package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.repository.Books;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    private final Books books;

    @Autowired
    BookService(Books books) {
        this.books = books;
    }

    public void save(Book book) {
        books.save(book);
        log.info("Book created: " + book);
    }

    public void update(Book book) {
        books.update(book);
        log.info("Book updated: " + book);
    }

    public void delete(Long id) {
        Book book = books.findById(id);
        books.delete(book);
        log.info("Book created: " + book);
    }

    public Book findById(Long id) {
        return books.findById(id);
    }

    public Page<Book> findAll(Pageable pageable) {
        return books.findAll(pageable);
    }

    public Book findByIsbn(String isbn) {
        return books.findByIsbn(isbn);
    }

    public Page<Book> findByLoanRequest(Long loanRequestId, Pageable pageable) {
        return books.findByLoanRequestId(loanRequestId, pageable);
    }

}
