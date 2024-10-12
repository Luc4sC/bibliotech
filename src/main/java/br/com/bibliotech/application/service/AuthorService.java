package br.com.bibliotech.application.service;

import br.com.bibliotech.application.converter.AuthorConverter;
import br.com.bibliotech.application.dto.AuthorDTO;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.service.AuthorDomainService;
import br.com.bibliotech.presentation.response.AuthorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorDomainService authorDomainService;

    private final AuthorConverter authorConverter = new AuthorConverter();

    public AuthorResponse save(AuthorDTO authorDTO){
        Author author = authorConverter.modelFromDTO(authorDTO);
        authorDomainService.save(author);

        return authorConverter.responseFromModel(author);
    }

    public AuthorResponse update(AuthorDTO authorDTO, Long id){
        Author author = authorDomainService.findById(id);
        Author authorUpdate = authorConverter.modelFromDTO(authorDTO);

        authorDomainService.update(author, authorUpdate);

        return authorConverter.responseFromModel(author);
    }

    public void delete(Long id){
        Author author = authorDomainService.findById(id);
        authorDomainService.delete(author);
    }

    public AuthorResponse findById(Long id){
        Author author = authorDomainService.findById(id);
        return authorConverter.responseFromModel(author);
    }

    public List<AuthorResponse> findAll(){
        List<Author> authors = authorDomainService.findAll();
        return authorConverter.responseListFromModelList(authors);
    }

    public AuthorResponse findByStageName(String stageName){
        Author author = authorDomainService.findByStageName(stageName);
        return authorConverter.responseFromModel(author);
    }

}
