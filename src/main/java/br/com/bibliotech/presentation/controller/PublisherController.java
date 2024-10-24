package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.service.PublisherService;
import br.com.bibliotech.presentation.converter.PublisherConverter;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.responses.PublisherResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    private final PublisherConverter publisherConverter;

    @Autowired
    PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
        this.publisherConverter = new PublisherConverter();
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> save(@RequestBody @Valid PublisherDTO publisherDTO) {
        Publisher publisher = publisherConverter.fromDto(publisherDTO);
        publisherService.save(publisher);

        return ResponseEntity.status(HttpStatus.CREATED).body(publisherConverter.fromModel(publisher));
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> update(@RequestBody @Valid PublisherDTO publisherDTO, @PathVariable Long id) {
        Publisher publisher = publisherService.findById(id);
        publisherService.update(publisher, publisherConverter.fromDto(publisherDTO));

        return ResponseEntity.status(HttpStatus.OK).body(publisherConverter.fromModel(publisher));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> delete(@PathVariable Long id){
        Publisher publisher = publisherService.findById(id);
        publisherService.delete(publisher);

        return ResponseEntity.status(HttpStatus.OK).body(publisherConverter.fromModel(publisher));
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> findById(@PathVariable Long id){
        Publisher publisher = publisherService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(publisherConverter.fromModel(publisher));
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<PublisherResponse>> findAll(){
        List<Publisher> publishers = publisherService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(publisherConverter.fromModelList(publishers));
    }

    @GetMapping(path = "/source", produces = "application/json; charset=utf-8")
    public ResponseEntity<PublisherResponse> findByStageName(@RequestParam String tradeName) {
        Publisher publisher = publisherService.findByTradeName(tradeName);

        return ResponseEntity.status(HttpStatus.OK).body(publisherConverter.fromModel(publisher));
    }
}
