package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.service.LoanService;
import br.com.bibliotech.presentation.converter.LoanConverter;
import br.com.bibliotech.presentation.responses.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("bibliotech/loan")
public class LoanController {

    private final LoanService loanService;
    private final LoanConverter loanConverter;

    @Autowired
    public LoanController(LoanService loanService, LoanConverter loanConverter) {
        this.loanService = loanService;
        this.loanConverter = loanConverter;
    }

    @PutMapping(path = "/{id}/finish", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        loanService.finish(id);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public LoanResponse findById(@PathVariable Long id) {
        Loan loan = loanService.findById(id);
        return loanConverter.fromModel(loan);
    }

    @GetMapping(produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.OK)
    public List<LoanResponse> findAll() {
        List<Loan> loans = loanService.findAll();
        return loanConverter.fromModelList(loans);
    }

}
