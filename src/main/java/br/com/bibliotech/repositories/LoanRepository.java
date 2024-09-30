package br.com.bibliotech.repositories;

import br.com.bibliotech.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
