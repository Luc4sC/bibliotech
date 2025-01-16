package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.repository.LoanRequests;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class LoanRequestsImpl implements LoanRequests {

    private final LoanRequestRepository loanRequestRepository;

    @Autowired
    LoanRequestsImpl(LoanRequestRepository loanRequestRepository) {
        this.loanRequestRepository = loanRequestRepository;
    }

    @Override
    @Transactional
    public void save(LoanRequest loanRequest) {
        loanRequestRepository.save(loanRequest);
    }

    @Override
    @Transactional
    public void update(LoanRequest loanRequest) {
        loanRequestRepository.flush();
    }

    @Override
    public LoanRequest findById(Long id) {
        Optional<LoanRequest> optionalRequest = loanRequestRepository.findById(id);
        if (optionalRequest.isEmpty()) {
            throw new NotFoundException("Request with id: " + id + " not exist!");
        }

        return optionalRequest.get();
    }

    @Override
    public Page<LoanRequest> findAll(Pageable pageable) {
        return loanRequestRepository.findAll(pageable);
    }
}
