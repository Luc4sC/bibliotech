package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.service.AuthorService;
import br.com.bibliotech.presentation.converter.AuthorConverter;
import br.com.bibliotech.presentation.dto.AuthorDTO;
import br.com.bibliotech.presentation.responses.AuthorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/author")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorConverter authorConverter;

    @Autowired
    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
        this.authorConverter = new AuthorConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> save(@RequestBody @Valid AuthorDTO authorDTO) {
        Author author = authorConverter.fromDto(authorDTO);
        authorService.save(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(authorConverter.fromModel(author));
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> update(@RequestBody @Valid AuthorDTO authorDTO, @PathVariable Long id) {
        Author author = authorService.findById(id);
        authorService.update(author, authorConverter.fromDto(authorDTO));

        return ResponseEntity.status(HttpStatus.OK).body(authorConverter.fromModel(author));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> delete(@PathVariable Long id){
        Author author = authorService.findById(id);
        authorService.delete(author);

        return ResponseEntity.status(HttpStatus.OK).body(authorConverter.fromModel(author));
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id){
        Author author = authorService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(authorConverter.fromModel(author));
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<AuthorResponse>> findAll(){
        List<Author> authors = authorService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(authorConverter.fromModelList(authors));
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    public ResponseEntity<AuthorResponse> findByStageName(@RequestParam String stageName) {
        Author author = authorService.findByStageName(stageName);

        return ResponseEntity.status(HttpStatus.OK).body(authorConverter.fromModel(author));
    }

}
