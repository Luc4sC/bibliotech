package br.com.bibliotech.services;

import br.com.bibliotech.dtos.PublisherDTO;
import br.com.bibliotech.repositories.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PublisherService {

    private PublisherRepository publisherRepository;

    public void save(PublisherDTO publisherDTO){
        br.com.bibliotech.entities.Publisher publisher = new br.com.bibliotech.entities.Publisher(publisherDTO);
        publisherRepository.save(publisher);
        log.info("Publish Company created: " + publisher);
    }

    public List<PublisherDTO> getAll(){
        List<br.com.bibliotech.entities.Publisher> publishingCompanies = publisherRepository.findAll();

        return convertEach(publishingCompanies);
    }

    public PublisherDTO getById(Long id){
        br.com.bibliotech.entities.Publisher publisher = findById(id);
        return PublisherDTO.converter(publisher);
    }

    public void update(PublisherDTO publisherDTO, Long id){
        br.com.bibliotech.entities.Publisher publisher = findById(id);

        publisher.setName(publisherDTO.name());
        publisher.setTradeName(publisherDTO.tradeName());
        publisher.setFoundationDate(publisherDTO.foundationDate());
    }

    public void delete(Long id){
        br.com.bibliotech.entities.Publisher publisher = findById(id);

        publisher.setDeleted(true);
    }

    private br.com.bibliotech.entities.Publisher findById(Long id){
        Optional<br.com.bibliotech.entities.Publisher> optionalPublisher = publisherRepository.findById(id);
        if (optionalPublisher.isEmpty())
            throw new RuntimeException();

        return optionalPublisher.get();
    }

    private List<PublisherDTO> convertEach(List<br.com.bibliotech.entities.Publisher> publishCompanies){
        List<PublisherDTO> publisherDTOS = new ArrayList<>();

        publishCompanies.forEach(publisher -> {
            PublisherDTO publisherDTO = PublisherDTO.converter(publisher);
            publisherDTOS.add(publisherDTO);
        });

        return publisherDTOS;
    }
}
