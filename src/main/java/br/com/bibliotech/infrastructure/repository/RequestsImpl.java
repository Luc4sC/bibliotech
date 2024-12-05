package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Request;
import br.com.bibliotech.domain.repository.Requests;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class RequestsImpl implements Requests {

    private final RequestRepository requestRepository;

    @Autowired
    RequestsImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    @Transactional
    public void save(Request request) {
        requestRepository.save(request);
    }

    @Override
    @Transactional
    public void update(Request request) {
        requestRepository.flush();
    }

    @Override
    public Request findById(Long id) {
        Optional<Request> optionalRequest = requestRepository.findById(id);
        if (optionalRequest.isEmpty()) {
            throw new NotFoundException("Request with id: " + id + " not exist!");
        }

        return optionalRequest.get();
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }
}
