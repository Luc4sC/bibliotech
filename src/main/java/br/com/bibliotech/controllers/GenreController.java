package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.GenreDTO;
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
    public ResponseEntity<GenreDTO> save(@RequestBody @Valid GenreDTO genreDTO){
        genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreDTO);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<GenreDTO>> findAll(){
        List<GenreDTO> genreDTOS = genreService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(genreDTOS);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreDTO> findById(@PathVariable Long id){
        GenreDTO genreDTO = genreService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(genreDTO);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreDTO> update(@RequestBody @Valid GenreDTO genreDTO, @PathVariable Long id){
        genreService.update(genreDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(genreDTO);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<GenreDTO> delete(@PathVariable Long id){
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
