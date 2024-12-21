package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.repository.Loans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanService {

    private final Loans loans;

    @Autowired
    public LoanService(Loans loans) {
        this.loans = loans;
    }

    public void save(Loan loan) {
        loans.save(loan);
        log.info("Loan created: " + loan);
    }

    public void update(Loan loan) {
        loans.update(loan);
        log.info("Loan updated: " + loan);
    }

    public Loan findById(Long id) {
        return loans.findById(id);
    }

    public List<Loan> findAll() {
        return loans.findAll();
    }

}
