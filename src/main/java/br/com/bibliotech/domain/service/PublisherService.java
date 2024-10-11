package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.PublisherResponseConverter;
import br.com.bibliotech.application.dto.PublisherDTO;
import br.com.bibliotech.domain.model.Address;
import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.infrastructure.repository.PublisherRepository;
import br.com.bibliotech.presentation.response.PublisherResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    private final PublisherResponseConverter publisherResponseConverter = new PublisherResponseConverter();

    @Transactional
    public PublisherResponse save(PublisherDTO publisherDTO){
        Publisher publisher = new Publisher(publisherDTO);
        publisherRepository.save(publisher);

        log.info("Publish Company created: " + publisher);
        return publisherResponseConverter.convert(publisher);
    }

    public List<PublisherResponse> getAll(){
        List<Publisher> publishingCompanies = publisherRepository.findAll();

        return publisherResponseConverter.convertEach(publishingCompanies);
    }

    public PublisherResponse getById(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        return publisherResponseConverter.convert(publisher);
    }

    @Transactional
    public PublisherResponse update(PublisherDTO publisherDTO, Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();

        publisher.setName(publisherDTO.name());
        publisher.setTradeName(publisherDTO.tradeName());
        publisher.setFoundationDate(publisherDTO.foundationDate());
        publisher.setAddress(new Address(publisherDTO.addressDTO()));

        return publisherResponseConverter.convert(publisher);
    }

    @Transactional
    public void delete(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        publisher.setDeleted(true);
        log.info("Publisher deleted: " + publisher);
    }

}
