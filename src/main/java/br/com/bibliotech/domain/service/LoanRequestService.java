package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.repository.LoanRequests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoanRequestService {

    private final LoanRequests loanRequests;

    @Autowired
    public LoanRequestService(LoanRequests loanRequests) {
        this.loanRequests = loanRequests;
    }

    public void save(LoanRequest loanRequest) {
        loanRequests.save(loanRequest);
        log.info("Request created: " + loanRequest);
    }

    public void update(LoanRequest loanRequest) {
        loanRequests.update(loanRequest);
        log.info("Request updated: " + loanRequest);
    }

    public LoanRequest findById(Long id) {
        return loanRequests.findById(id);
    }

    public Page<LoanRequest> findAll(Pageable pageable) {
        return loanRequests.findAll(pageable);
    }

}
