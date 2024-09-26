package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.AuthorDTO;
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
    public ResponseEntity<AuthorDTO> save(@RequestBody @Valid AuthorDTO authorDTO){
        authorService.save(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorDTO);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<AuthorDTO> authorDTOS = authorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(authorDTOS);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id){
        AuthorDTO authorDTO = authorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorDTO);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorDTO> update(@RequestBody @Valid AuthorDTO authorDTO, @PathVariable Long id){
        authorService.update(authorDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(authorDTO);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorDTO> delete(@PathVariable Long id){
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
