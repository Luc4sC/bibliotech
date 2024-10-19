package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.repository.Publishers;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class PublishersImpl implements Publishers {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    @Transactional
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    @Transactional
    public void delete(Publisher publisher) {
        publisher.setDeleted(true);
    }

    @Override
    @Transactional
    public void update(Publisher publisher, Publisher publisherUpdate) {
        publisher.setName(publisherUpdate.getName());
        publisher.setTradeName(publisherUpdate.getTradeName());
        publisher.setFoundationDate(publisherUpdate.getFoundationDate());
        publisher.setAddress(publisherUpdate.getAddress());
    }

    @Override
    public Publisher findById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isEmpty())
            throw new NotFoundException("Publisher with id: " + id + " not found!");

        return publisherOptional.get();
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher findByTradeName(String tradeName) {
        Optional<Publisher> publisherOptional = publisherRepository.findByTradeName(tradeName);
        if (publisherOptional.isEmpty())
            throw new NotFoundException("Publisher with trade name: " + tradeName + " not found!");

        return publisherOptional.get();
    }
}
