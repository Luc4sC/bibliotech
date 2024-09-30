package br.com.bibliotech.controllers;

import br.com.bibliotech.dtos.LoanDTO;
import br.com.bibliotech.responses.LoanResponse;
import br.com.bibliotech.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bibliotech/loan")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<LoanResponse> save(@RequestBody @Valid LoanDTO loanDTO){
        LoanResponse loanResponse = loanService.start(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<LoanResponse> update(@PathVariable Long id){
        LoanResponse loanResponse = loanService.finish(id);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }
}
