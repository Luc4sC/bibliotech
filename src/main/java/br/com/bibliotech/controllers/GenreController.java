package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.GenreDTO;
import br.com.bibliotech.responses.GenreResponse;
import br.com.bibliotech.services.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> save(@RequestBody @Valid GenreDTO genreDTO){
        GenreResponse genreResponse = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<GenreResponse>> findAll(){
        List<GenreResponse> genreResponses = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(genreResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> findById(@PathVariable Long id){
        GenreResponse genreResponse = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(genreResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> update(@RequestBody @Valid GenreDTO genreDTO, @PathVariable Long id){
        GenreResponse genreResponse = genreService.update(genreDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(genreResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreResponse> delete(@PathVariable Long id){
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
