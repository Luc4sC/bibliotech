package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.service.GenreService;
import br.com.bibliotech.presentation.converter.GenreConverter;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.response.GenreResponse;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;
    private final GenreConverter genreConverter;

    @Autowired
    GenreController(GenreService genreService, GenreConverter genreConverter) {
        this.genreService = genreService;
        this.genreConverter = genreConverter;
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
    public GenreResponse findByName(@PathVariable Long id){
        Genre genre = genreService.findById(id);
        return genreConverter.fromModel(genre);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    @PageableAsQueryParam
    public List<GenreResponse> findAll(Pageable pageable){
        Page<Genre> genres = genreService.findAll(pageable);
        return genreConverter.fromPage(genres);
    }

    @GetMapping(path = "/{name}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public GenreResponse findByName(@PathVariable String name) {
        Genre genre = genreService.findByName(name);
        return genreConverter.fromModel(genre);
    }

}
