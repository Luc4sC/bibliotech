package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.LoanRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanRequests {

    void save(LoanRequest loanRequest);
    void update(LoanRequest loanRequest);
    LoanRequest findById(Long id);
    Page<LoanRequest> findAll(Pageable pageable);

}
