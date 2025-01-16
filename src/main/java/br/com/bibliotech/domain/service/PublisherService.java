package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.repository.Publishers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void update(Publisher publisher) {
        publishers.update(publisher);
        log.info("Publisher updated: " + publisher);
    }

    public void delete(Long id) {
        Publisher publisher = publishers.findById(id);
        publishers.delete(publisher);

        log.info("Publisher deleted: " + publisher);
    }

    public Publisher findById(Long id) {
        return publishers.findById(id);
    }

    public Page<Publisher> findAll(Pageable pageable) {
        return publishers.findAll(pageable);
    }

    public Publisher findByTradeName(String tradeName) {
        return publishers.findByTradeName(tradeName);
    }

}
