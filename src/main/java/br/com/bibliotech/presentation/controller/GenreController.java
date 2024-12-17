package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.service.GenreService;
import br.com.bibliotech.presentation.converter.GenreConverter;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.responses.GenreResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/genre")
public class GenreController {

    private final GenreService genreService;
    private final GenreConverter genreConverter;

    @Autowired
    GenreController(GenreService genreService) {
        this.genreService = genreService;
        this.genreConverter = new GenreConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid GenreDTO genreDTO) {
        Genre genre = genreConverter.fromDTO(genreDTO);
        genreService.save(genre);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid GenreDTO genreDTO, @PathVariable Long id) {
        genreService.update(genreConverter.fromDTO(id, genreDTO));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        genreService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public GenreResponse findById(@PathVariable Long id){
        Genre genre = genreService.findById(id);
        return genreConverter.fromModel(genre);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<GenreResponse> findAll(){
        List<Genre> genres = genreService.findAll();
        return genreConverter.fromModelList(genres);
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public GenreResponse findById(@RequestParam String name) {
        Genre genre = genreService.findByName(name);
        return genreConverter.fromModel(genre);
    }

}
