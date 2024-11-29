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
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @Autowired
    BookService(Books books, AuthorService authorService, CategoryService categoryService, GenreService genreService,
                PublisherService publisherService) {
        this.books = books;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.genreService = genreService;
        this.publisherService = publisherService;
    }

    public void save(Book book, Long authorId, Long categoryId, Long genreId, Long publisherId) {
        authorService.findById(authorId).addBook(book);
        categoryService.findById(categoryId).addBook(book);
        genreService.findById(genreId).addBook(book);
        publisherService.findById(publisherId).addBook(book);

        books.save(book);
        log.info("Book created: " + book);
    }

    public void update(Long bookId, Book bookUpdate, Long authorId, Long categoryId, Long genreId, Long publisherId) {
        Book book = books.findById(bookId);

        authorService.findById(authorId).addBook(bookUpdate);
        categoryService.findById(categoryId).addBook(bookUpdate);
        genreService.findById(genreId).addBook(bookUpdate);
        publisherService.findById(publisherId).addBook(bookUpdate);

        books.update(book, bookUpdate);
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

}
