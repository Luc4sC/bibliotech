package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.repository.Publishers;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
        try {
            publisherRepository.save(publisher);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Publisher with legal or trade name already exist!");
        }
    }

    @Override
    @Transactional
    public void update(Publisher publisher) {
        try {
            publisherRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Publisher with legal or trade name already exist!");
        }
    }

    @Override
    @Transactional
    public void delete(Publisher publisher) {
        publisher.delete();
        publisherRepository.flush();
    }

    @Override
    public Publisher findById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (publisherOptional.isEmpty())
            throw new NotFoundException("Publisher with id: " + id + " not found!");

        return publisherOptional.get();
    }

    @Override
    public Page<Publisher> findAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    @Override
    public Publisher findByTradeName(String tradeName) {
        Optional<Publisher> publisherOptional = publisherRepository.findByTradeName(tradeName);
        if (publisherOptional.isEmpty())
            throw new NotFoundException("Publisher with trade name: " + tradeName + " not found!");

        return publisherOptional.get();
    }

}
