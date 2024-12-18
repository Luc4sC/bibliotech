package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.*;
import br.com.bibliotech.domain.repository.Books;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Book> findAll() {
        return books.findAll();
    }

    public Book findByIsbn(String isbn) {
        return books.findByIsbn(isbn);
    }

    public List<Book> findByLoanRequest(Long loanRequestId) {
        return books.findAll();
    }

}
