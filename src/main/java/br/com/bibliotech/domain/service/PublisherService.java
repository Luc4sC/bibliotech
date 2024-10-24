package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.repository.Publishers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublisherService {

    private final Publishers publishers;

    @Autowired
    PublisherService(Publishers publishers) {
        this.publishers = publishers;
    }

    public void save(Publisher publisher) {
        publishers.save(publisher);
        log.info("Publisher created: " + publisher);
    }

    public void update(Publisher publisher, Publisher update) {
        publishers.update(publisher, update);
        log.info("Publisher updated: " + publisher);
    }

    public void delete(Publisher publisher) {
        publishers.delete(publisher);
        log.info("Publisher deleted: " + publisher);
    }

    public Publisher findById(Long id) {
        return publishers.findById(id);
    }

    public List<Publisher> findAll() {
        return publishers.findAll();
    }

    public Publisher findByTradeName(String tradeName) {
        return publishers.findByTradeName(tradeName);
    }

}
