package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
