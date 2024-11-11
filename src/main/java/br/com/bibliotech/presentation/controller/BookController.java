package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.*;
import br.com.bibliotech.domain.service.*;
import br.com.bibliotech.presentation.converter.BookConverter;
import br.com.bibliotech.presentation.dto.BookDTO;
import br.com.bibliotech.presentation.responses.BookResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/book")
public class BookController {

    private final BookService bookService;
    private final BookConverter bookConverter;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final GenreService genreService;
    private final PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService,
                          GenreService genreService, PublisherService publisherService) {
        this.bookService = bookService;
        this.bookConverter = new BookConverter();
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.genreService = genreService;
        this.publisherService = publisherService;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid BookDTO bookDTO) {
        Author author = authorService.findById(bookDTO.authorId());
        Category category = categoryService.findById(bookDTO.categoryId());
        Genre genre = genreService.findById(bookDTO.genreId());
        Publisher publisher = publisherService.findById(bookDTO.publisherId());
        Book book = bookConverter.fromDTO(bookDTO, author, category,
                genre, publisher);

        bookService.save(book);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) {
        Author author = authorService.findById(bookDTO.authorId());
        Category category = categoryService.findById(bookDTO.categoryId());
        Genre genre = genreService.findById(bookDTO.genreId());
        Publisher publisher = publisherService.findById(bookDTO.publisherId());
        Book book = bookConverter.fromDTO(bookDTO, author, category,
                genre, publisher);

        bookService.update(id, book);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findById(@PathVariable Long id){
        Book book = bookService.findById(id);
        return bookConverter.fromModel(book);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
        public List<BookResponse> findAll(){
        List<Book> books = bookService.findAll();
        return bookConverter.fromModelList(books);
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findByIsbn(@RequestParam String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return bookConverter.fromModel(book);
    }

}
