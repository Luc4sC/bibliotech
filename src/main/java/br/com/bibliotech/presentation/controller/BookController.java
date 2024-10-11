package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.dto.BookDTO;
import br.com.bibliotech.presentation.response.BookResponse;
import br.com.bibliotech.domain.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<BookResponse> save(@RequestBody @Valid BookDTO bookDTO){
        BookResponse bookResponse = bookService.save(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<BookResponse>> findAll(){
        List<BookResponse> bookResponses = bookService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<BookResponse> findById(@PathVariable Long id){
        BookResponse bookResponse = bookService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<BookResponse> update(@RequestBody @Valid BookDTO bookDTO, @PathVariable Long id){
        BookResponse bookResponse = bookService.update(bookDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<BookResponse> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
