package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.service.GenreService;
import br.com.bibliotech.presentation.converter.GenreConverter;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.responses.GenreResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GenreResponse> save(@RequestBody @Valid GenreDTO genreDTO) {
        Genre genre = genreConverter.fromDto(genreDTO);
        genreService.save(genre);

        return ResponseEntity.status(HttpStatus.CREATED).body(genreConverter.fromModel(genre));
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> update(@RequestBody @Valid GenreDTO genreDTO, @PathVariable Long id) {
        Genre genre = genreService.findById(id);
        genreService.update(genre, genreConverter.fromDto(genreDTO));

        return ResponseEntity.status(HttpStatus.OK).body(genreConverter.fromModel(genre));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> delete(@PathVariable Long id){
        Genre genre = genreService.findById(id);
        genreService.delete(genre);

        return ResponseEntity.status(HttpStatus.OK).body(genreConverter.fromModel(genre));
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> findById(@PathVariable Long id){
        Genre genre = genreService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(genreConverter.fromModel(genre));
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<GenreResponse>> findAll(){
        List<Genre> genres = genreService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(genreConverter.fromModelList(genres));
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> findById(@RequestParam String name) {
        Genre genre = genreService.findByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(genreConverter.fromModel(genre));
    }

}
