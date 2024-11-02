package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Book;
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

    public void update(Book book, Book update) {
        books.update(book, update);
        log.info("Book updated: " + book);
    }

    public void delete(Book book) {
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

}
