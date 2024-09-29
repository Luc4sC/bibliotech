package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.PublisherDTO;
import br.com.bibliotech.responses.PublisherResponse;
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
    public ResponseEntity<PublisherResponse> save(@RequestBody @Valid PublisherDTO publisherDTO){
        PublisherResponse publisherResponse = publisherService.save(publisherDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherResponse);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<PublisherResponse>> findAll(){
        List<PublisherResponse> publisherResponses = publisherService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(publisherResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> findById(@PathVariable Long id){
        PublisherResponse publisherResponse = publisherService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(publisherResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> update(@RequestBody @Valid PublisherDTO publisherDTO, @PathVariable Long id){
        PublisherResponse publisherResponse = publisherService.update(publisherDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(publisherResponse);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> delete(@PathVariable Long id){
        publisherService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


}
