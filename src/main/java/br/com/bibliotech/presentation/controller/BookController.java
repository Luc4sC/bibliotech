package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.service.BookService;
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

    @Autowired
    public BookController(BookService bookService, BookConverter bookConverter) {
        this.bookService = bookService;
        this.bookConverter = bookConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid BookDTO bookDTO) {
        Book book = bookConverter.fromDTO(bookDTO);
        bookService.save(book);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid BookDTO bookDTO) {
        Book bookUpdate = bookConverter.fromDTO(bookDTO);
        bookService.update(id, bookUpdate);
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
