package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.PublisherDTO;
import br.com.bibliotech.services.PublisherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherDTO> save(@RequestBody @Valid PublisherDTO publisherDTO){
        publisherService.save(publisherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherDTO);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<PublisherDTO>> findAll(){
        List<PublisherDTO> publisherDTOS = publisherService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(publisherDTOS);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherDTO> findById(@PathVariable Long id){
        PublisherDTO publisherDTO = publisherService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(publisherDTO);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherDTO> update(@RequestBody @Valid PublisherDTO publisherDTO, @PathVariable Long id){
        publisherService.update(publisherDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(publisherDTO);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherDTO> delete(@PathVariable Long id){
        publisherService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
