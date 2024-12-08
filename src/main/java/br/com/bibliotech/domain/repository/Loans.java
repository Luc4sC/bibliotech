package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Loan;

import java.util.List;

public interface Loans {

    void save(Loan loan);
    void update();
    Loan findById(Long id);
    List<Loan> findAll();

}
