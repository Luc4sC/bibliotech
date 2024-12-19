package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.BookLoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

interface BookRequestRepository extends JpaRepository<BookLoanRequest, Long> {
}
