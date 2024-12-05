package br.com.bibliotech.domain.repository;

import br.com.bibliotech.domain.model.Request;

import java.util.List;

public interface Requests {

    void save(Request request);
    void update(Request request);
    Request findById(Long id);
    List<Request> findAll();

}
