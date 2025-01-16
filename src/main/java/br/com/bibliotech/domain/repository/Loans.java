package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Loans {

    void save(Loan loan);
    void update(Loan loan);
    Loan findById(Long id);
    Page<Loan> findAll(Pageable pageable);

}
