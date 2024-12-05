package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Request;
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

    public void save(Request request) {
        requests.save(request);
        log.info("Request created: " + request);
    }

    public void update(Request request) {
        requests.update(request);
        log.info("Request updated: " + request);
    }

    public Request findById(Long id) {
        return requests.findById(id);
    }

    public List<Request> findAll() {
        return requests.findAll();
    }

}
