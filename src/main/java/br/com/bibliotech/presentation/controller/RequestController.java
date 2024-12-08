package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.service.RequestUseCases;
import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.service.*;
import br.com.bibliotech.presentation.converter.RequestConverter;
import br.com.bibliotech.presentation.dto.RequestAcceptedDTO;
import br.com.bibliotech.presentation.dto.RequestDTO;
import br.com.bibliotech.presentation.responses.RequestResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/request")
public class RequestController {

    private final RequestService requestService;
    private final RequestUseCases requestUseCases;
    private final RequestConverter requestConverter;

    @Autowired
    public RequestController(RequestService requestService, RequestUseCases requestUseCases, RequestConverter requestConverter) {
        this.requestService = requestService;
        this.requestUseCases = requestUseCases;
        this.requestConverter = requestConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody @Valid RequestDTO requestDTO) {
        requestUseCases.createRequest(requestDTO.userId(), requestDTO.booksIds());
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public RequestResponse findById(@PathVariable Long id) {
        Request request = requestService.findById(id);
        return requestConverter.fromModel(request);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestResponse> findAll() {
        List<Request> requests = requestService.findAll();
        return requestConverter.fromModelList(requests);
    }

    @PutMapping(path = "/accept", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void accept(RequestAcceptedDTO requestAcceptedDTO) {
        requestUseCases.accept(requestAcceptedDTO.id(), requestAcceptedDTO.endDate());
    }

    @PutMapping(path = "reject/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reject(@PathVariable Long id) {
        requestUseCases.reject(id);
    }

}
