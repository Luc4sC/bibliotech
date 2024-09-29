package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.AuthorDTO;
import br.com.bibliotech.responses.AuthorResponse;
import br.com.bibliotech.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> save(@RequestBody @Valid AuthorDTO authorDTO){
        AuthorResponse authorResponse = authorService.save(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<AuthorResponse>> findAll(){
        List<AuthorResponse> authorResponses = authorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(authorResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id){
        AuthorResponse authorResponse = authorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> update(@RequestBody @Valid AuthorDTO authorDTO, @PathVariable Long id){
        AuthorResponse authorResponse = authorService.update(authorDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> delete(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
