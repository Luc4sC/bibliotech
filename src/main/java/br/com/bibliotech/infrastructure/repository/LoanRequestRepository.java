package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
}
