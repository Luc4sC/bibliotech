package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.repository.Publishers;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class PublishersImpl implements Publishers {

    private final PublisherRepository publisherRepository;

    @Autowired
    PublishersImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    @Transactional
    public void save(Publisher publisher) {
        verify(publisher);
        publisherRepository.save(publisher);
    }

    private void verify(Publisher publisher) {
        if (publisherRepository.existsByLegalName(publisher.getLegalName()))
            throw new ConflictException("Publisher with legal name: " + publisher.getLegalName() + " already exist!");

        if (publisherRepository.existsByTradeName(publisher.getTradeName()))
            throw new ConflictException("Publisher with trade name: " + publisher.getTradeName() + " already exist!");
    }

    @Override
    @Transactional
    public void update(Publisher publisher, Publisher publisherUpdate) {
        publisher.update(publisherUpdate);
    }

    @Override
    @Transactional
    public void delete(Publisher publisher) {
        publisher.delete();
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
