package br.com.bibliotech.presentation.controller;

import br.com.bibliotech.application.dto.LoanDTO;
import br.com.bibliotech.domain.service.AuthorDomainService;
import br.com.bibliotech.presentation.response.LoanResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bibliotech/loan")
public class LoanController {

    @Autowired
    private AuthorDomainService.LoanService loanService;

    @GetMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<List<LoanResponse>> getAll(){
        List<LoanResponse> loanResponses = loanService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(loanResponses);
    }

    @GetMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<LoanResponse> getById(@PathVariable Long id){
        LoanResponse loanResponse = loanService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }

    @PostMapping(produces = "application/json; charset=utf-8")
    public ResponseEntity<LoanResponse> start(@RequestBody @Valid LoanDTO loanDTO){
        LoanResponse loanResponse = loanService.start(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponse);
    }

    @PutMapping(path = "/{id}", produces = "application/json; charset=utf-8")
    public ResponseEntity<LoanResponse> finish(@PathVariable Long id){
        LoanResponse loanResponse = loanService.finish(id);
        return ResponseEntity.status(HttpStatus.OK).body(loanResponse);
    }
}
