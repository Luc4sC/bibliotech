package br.com.bibliotech.services;

import br.com.bibliotech.convertes.PublisherResponseConverter;
import br.com.bibliotech.dtos.PublisherDTO;
import br.com.bibliotech.entities.Address;
import br.com.bibliotech.entities.Publisher;
import br.com.bibliotech.repositories.PublisherRepository;
import br.com.bibliotech.responses.PublisherResponse;
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

    @Transactional
    public PublisherResponse save(PublisherDTO publisherDTO){
        br.com.bibliotech.entities.Publisher publisher = new br.com.bibliotech.entities.Publisher(publisherDTO);
        publisherRepository.save(publisher);

        log.info("Publish Company created: " + publisher);
        return PublisherResponseConverter.convert(publisher);
    }

    public List<PublisherResponse> getAll(){
        List<Publisher> publishingCompanies = publisherRepository.findAll();

        return PublisherResponseConverter.convertList(publishingCompanies);
    }

    public PublisherResponse getById(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        return PublisherResponseConverter.convert(publisher);
    }

    @Transactional
    public PublisherResponse update(PublisherDTO publisherDTO, Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();

        publisher.setName(publisherDTO.name());
        publisher.setTradeName(publisherDTO.tradeName());
        publisher.setFoundationDate(publisherDTO.foundationDate());
        publisher.setAddress(new Address(publisherDTO.addressDTO()));

        return PublisherResponseConverter.convert(publisher);
    }

    @Transactional
    public void delete(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow();
        publisher.setDeleted(true);
        log.info("Publisher deleted: " + publisher);
    }

}
