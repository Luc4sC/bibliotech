package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.service.AuthorService;
import br.com.bibliotech.presentation.converter.AuthorConverter;
import br.com.bibliotech.presentation.dto.AuthorDTO;
import br.com.bibliotech.presentation.response.AuthorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/author")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorConverter authorConverter;

    @Autowired
    AuthorController(AuthorService authorService, AuthorConverter authorConverter) {
        this.authorService = authorService;
        this.authorConverter = authorConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid AuthorDTO authorDTO) {
        Author author = authorConverter.fromDTO(authorDTO);
        authorService.save(author);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid AuthorDTO authorDTO, @PathVariable Long id) {
        authorService.update(authorConverter.fromDTO(id, authorDTO));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse findById(@PathVariable Long id){
        Author author = authorService.findById(id);
        return authorConverter.fromModel(author);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorResponse> findAll(){
        List<Author> authors = authorService.findAll();
        return authorConverter.fromModelList(authors);
    }

    @GetMapping(path = "/{stageName}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public AuthorResponse findByStageName(@PathVariable String stageName) {
        Author author = authorService.findByStageName(stageName);
        return authorConverter.fromModel(author);
    }

}
