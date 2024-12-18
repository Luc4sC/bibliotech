package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.service.RequestUseCases;
import br.com.bibliotech.domain.model.LoanRequest;
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
public class LoanRequestController {

    private final LoanRequestService loanRequestService;
    private final RequestUseCases requestUseCases;
    private final RequestConverter requestConverter;

    @Autowired
    public LoanRequestController(LoanRequestService loanRequestService, RequestUseCases requestUseCases, RequestConverter requestConverter) {
        this.loanRequestService = loanRequestService;
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
        LoanRequest loanRequest = loanRequestService.findById(id);
        return requestConverter.fromModel(loanRequest);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestResponse> findAll() {
        List<LoanRequest> loanRequests = loanRequestService.findAll();
        return requestConverter.fromModelList(loanRequests);
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
