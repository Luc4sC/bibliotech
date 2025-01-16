package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Book;
import br.com.bibliotech.domain.service.BookService;
import br.com.bibliotech.presentation.converter.BookConverter;
import br.com.bibliotech.presentation.dto.BookDTO;
import br.com.bibliotech.presentation.dto.BookUpdateDTO;
import br.com.bibliotech.presentation.response.BookResponse;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void update(@PathVariable Long id, @RequestBody @Valid BookUpdateDTO bookDTO) {
        Book book = bookConverter.fromDTO(id, bookDTO);
        bookService.update(book);
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
    @PageableAsQueryParam
    public List<BookResponse> findAll(Pageable pageable){
        Page<Book> books = bookService.findAll(pageable);
        return bookConverter.fromPage(books);
    }

    @GetMapping(path = "/{isbn}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findByIsbn(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        return bookConverter.fromModel(book);
    }

    @GetMapping(path = "/loanRequest", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    @PageableAsQueryParam
    public List<BookResponse> findBooksByLoanRequest(@RequestParam Long loanRequestId, Pageable pageable) {
        Page<Book> books = bookService.findByLoanRequest(loanRequestId, pageable);
        return bookConverter.fromPage(books);
    }
}
