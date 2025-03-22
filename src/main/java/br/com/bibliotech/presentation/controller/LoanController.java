package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.usecase.FinishLoanUseCase;
import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.service.LoanService;
import br.com.bibliotech.presentation.converter.LoanConverter;
import br.com.bibliotech.presentation.response.LoanResponse;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;
    private final LoanConverter loanConverter;
    private final FinishLoanUseCase finishLoanUseCase;

    @Autowired
    public LoanController(LoanService loanService, LoanConverter loanConverter, FinishLoanUseCase finishLoanUseCase) {
        this.loanService = loanService;
        this.loanConverter = loanConverter;
        this.finishLoanUseCase = finishLoanUseCase;
    }

    @PutMapping(path = "/{id}/finish", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        finishLoanUseCase.finish(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public LoanResponse findById(@PathVariable Long id) {
        Loan loan = loanService.findById(id);
        return loanConverter.fromModel(loan);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    @PageableAsQueryParam
    public List<LoanResponse> findAll(Pageable pageable) {
        Page<Loan> loans = loanService.findAll(pageable);
        return loanConverter.fromPage(loans);
    }

}
