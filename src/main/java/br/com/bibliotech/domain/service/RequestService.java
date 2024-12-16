package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.LoanRequest;
import br.com.bibliotech.domain.repository.Requests;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RequestService {

    private final Requests requests;

    @Autowired
    public RequestService(Requests requests) {
        this.requests = requests;
    }

    public void save(LoanRequest loanRequest) {
        requests.save(loanRequest);
        log.info("Request created: " + loanRequest);
    }

    public void update(LoanRequest loanRequest) {
        requests.update(loanRequest);
        log.info("Request updated: " + loanRequest);
    }

    public LoanRequest findById(Long id) {
        return requests.findById(id);
    }

    public List<LoanRequest> findAll() {
        return requests.findAll();
    }

}
