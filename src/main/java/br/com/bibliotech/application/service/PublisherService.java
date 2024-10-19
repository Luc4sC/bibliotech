package br.com.bibliotech.application.service;

import br.com.bibliotech.application.converter.PublisherConverter;
import br.com.bibliotech.application.dto.PublisherDTO;
import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.model.Publisher;
import br.com.bibliotech.domain.service.PublisherDomainService;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import br.com.bibliotech.presentation.response.PublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherDomainService publisherDomainService;

    private final PublisherConverter publisherConverter = new PublisherConverter();

    public PublisherResponse save(PublisherDTO publisherDTO){
        Publisher publisher = publisherConverter.modelFromDTO(publisherDTO);

        validTradeName(publisher.getTradeName(), publisher);
        publisherDomainService.save(publisher);

        return publisherConverter.responseFromModel(publisher);
    }

    public PublisherResponse update(PublisherDTO publisherDTO, Long id){
        Publisher publisher = publisherDomainService.findById(id);
        Publisher publisherUpdate = publisherConverter.modelFromDTO(publisherDTO);

        validTradeName(publisherUpdate.getTradeName(), publisher);
        publisherDomainService.update(publisher, publisherUpdate);

        return publisherConverter.responseFromModel(publisher);
    }

    public void delete(Long id){
        Publisher publisher = publisherDomainService.findById(id);
        publisherDomainService.delete(publisher);
    }

    public PublisherResponse findById(Long id){
        Publisher publisher = publisherDomainService.findById(id);
        return publisherConverter.responseFromModel(publisher);
    }

    public List<PublisherResponse> findAll(){
        List<Publisher> publishers = publisherDomainService.findAll();
        return publisherConverter.responseListFromModelList(publishers);
    }

    public PublisherResponse findByTradeName(String tradeName){
        Publisher publisher = publisherDomainService.findByTradeName(tradeName);
        return publisherConverter.responseFromModel(publisher);
    }

    private void validTradeName(String tradeName, Publisher publisher){
        try {
            Publisher byTradeName = publisherDomainService.findByTradeName(tradeName);
            if (!publisher.equals(byTradeName))
                throw new BadRequestException("Publisher with trade name: " + tradeName + " already exist!");

        } catch (NotFoundException ignored){}
    }

}
