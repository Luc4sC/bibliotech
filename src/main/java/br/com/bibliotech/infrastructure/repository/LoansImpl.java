package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Loan;
import br.com.bibliotech.domain.repository.Loans;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class LoansImpl implements Loans {

    private final LoanRepository loanRepository;

    @Autowired
    LoansImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

    @Transactional
    @Override
    public void update() {
        loanRepository.flush();
    }

    @Override
    public Loan findById(Long id) {
        Optional<Loan> optionalLoan = loanRepository.findById(id);
        if (optionalLoan.isEmpty()) {
            throw new NotFoundException("Loan with id: " + id + " not exist");
        }

        return optionalLoan.get();
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }
}
