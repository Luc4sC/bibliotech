package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.usecase.AcceptRequestUseCase;
import br.com.bibliotech.application.usecase.RejectRequestUseCase;
import br.com.bibliotech.application.usecase.CreateLoanRequestUseCase;
import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.service.*;
import br.com.bibliotech.presentation.converter.LoanRequestConverter;
import br.com.bibliotech.presentation.dto.RequestAcceptedDTO;
import br.com.bibliotech.presentation.dto.LoanRequestDTO;
import br.com.bibliotech.presentation.response.LoanRequestResponse;
import jakarta.validation.Valid;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanRequest")
public class LoanRequestController {

    private final LoanRequestService loanRequestService;
    private final CreateLoanRequestUseCase createLoanRequestUseCase;
    private final AcceptRequestUseCase acceptRequestUseCase;
    private final RejectRequestUseCase rejectRequestUseCase;
    private final LoanRequestConverter loanRequestConverter;

    @Autowired
    public LoanRequestController(LoanRequestService loanRequestService, CreateLoanRequestUseCase createLoanRequestUseCase,
                                 RejectRequestUseCase rejectRequestUseCase, AcceptRequestUseCase acceptRequestUseCase,
                                 LoanRequestConverter loanRequestConverter) {
        this.loanRequestService = loanRequestService;
        this.createLoanRequestUseCase = createLoanRequestUseCase;
        this.acceptRequestUseCase = acceptRequestUseCase;
        this.rejectRequestUseCase = rejectRequestUseCase;
        this.loanRequestConverter = loanRequestConverter;
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public void request(@RequestBody @Valid LoanRequestDTO loanRequestDTO) {
        createLoanRequestUseCase.createRequest(loanRequestDTO.userEmail(), loanRequestDTO.booksIds());
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public LoanRequestResponse findById(@PathVariable Long id) {
        LoanRequest loanRequest = loanRequestService.findById(id);
        return loanRequestConverter.fromModel(loanRequest);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    @PageableAsQueryParam
    public List<LoanRequestResponse> findAll(Pageable pageable) {
        Page<LoanRequest> loanRequests = loanRequestService.findAll(pageable);
        return loanRequestConverter.fromPage(loanRequests);
    }

    @PutMapping(path = "/{id}/accept", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void accept(@PathVariable Long id, @RequestBody @Valid RequestAcceptedDTO requestAcceptedDTO) {
        acceptRequestUseCase.accept(id, requestAcceptedDTO.endDate());
    }

    @PutMapping(path = "/{id}/reject", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reject(@PathVariable Long id) {
        rejectRequestUseCase.reject(id);
    }

}
