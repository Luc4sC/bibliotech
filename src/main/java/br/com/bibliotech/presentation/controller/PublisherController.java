package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.service.PublisherService;
import br.com.bibliotech.presentation.converter.PublisherConverter;
import br.com.bibliotech.presentation.dto.PublisherDTO;
import br.com.bibliotech.presentation.response.PublisherResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final PublisherConverter publisherConverter;

    @Autowired
    PublisherController(PublisherService publisherService, PublisherConverter publisherConverter) {
        this.publisherService = publisherService;
        this.publisherConverter = publisherConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid PublisherDTO publisherDTO) {
        Publisher publisher = publisherConverter.fromDTO(publisherDTO);
        publisherService.save(publisher);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid PublisherDTO publisherDTO, @PathVariable Long id) {
        publisherService.update(publisherConverter.fromDTO(id, publisherDTO));
    }

    @DeleteMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        publisherService.delete(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public PublisherResponse findById(@PathVariable Long id) {
        Publisher publisher = publisherService.findById(id);
        return publisherConverter.fromModel(publisher);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherResponse> findAll() {
        List<Publisher> publishers = publisherService.findAll();
        return publisherConverter.fromModelList(publishers);
    }

    @GetMapping(path = "/{tradeName}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public PublisherResponse findByStageName(@PathVariable String tradeName) {
        Publisher publisher = publisherService.findByTradeName(tradeName);
        return publisherConverter.fromModel(publisher);
    }
}
