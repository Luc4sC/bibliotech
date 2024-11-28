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
        book.setAuthor(authorService.findById(authorId));
        book.setCategory(categoryService.findById(categoryId));
        book.setGenre(genreService.findById(genreId));
        book.setPublisher(publisherService.findById(publisherId));

        books.save(book);
        log.info("Book created: " + book);
    }

    public void update(Long bookId, Book bookUpdate, Long authorId, Long categoryId, Long genreId, Long publisherId) {
        Book book = books.findById(bookId);
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
