package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.converter.AuthorResponseConverter;
import br.com.bibliotech.application.dto.AuthorDTO;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.application.exception.NotFoundException;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.presentation.response.AuthorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private Authors authors;

    private final AuthorResponseConverter authorResponseConverter = new AuthorResponseConverter();

    public AuthorResponse save(AuthorDTO authorDTO){
        if (authorExist(authorDTO.stageName()))
            throw new RuntimeException();

        Author author = new Author(authorDTO);

        authors.save(author);
        log.info("Author Created: " + author);

        return authorResponseConverter.convert(author);
    }

    public AuthorResponse update(AuthorDTO authorDTO, Long id){
        Author author = authors.findById(id);

        authors.update(author, authorDTO);
        log.info("Author updated: " + author);

        return authorResponseConverter.convert(author);
    }

    public void delete(Long id){
        Author author = authors.findById(id);
        authors.delete(author);

        log.info("Author deleted: " + author);
    }

    public AuthorResponse findById(Long id){
        Author author = authors.findById(id);
        return authorResponseConverter.convert(author);
    }

    public List<AuthorResponse> findAll(){
        List<Author> authors = this.authors.findAll();
        return authorResponseConverter.convertEach(authors);
    }

    public AuthorResponse findByStageName(String stageName){
        Author author = authors.findByStageName(stageName);
        return authorResponseConverter.convert(author);
    }

    private boolean authorExist(String stageName){
        try {
            authors.findByStageName(stageName);
            return true;
        } catch (NotFoundException e){
            return false;
        }
    }

}
