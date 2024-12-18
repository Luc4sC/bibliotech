package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.LoanRequest;

import java.util.List;

public interface LoanRequests {

    void save(LoanRequest loanRequest);
    void update(LoanRequest loanRequest);
    LoanRequest findById(Long id);
    List<LoanRequest> findAll();

}
