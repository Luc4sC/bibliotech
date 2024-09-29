package br.com.bibliotech.services;

import br.com.bibliotech.convertes.AuthorResponseConverter;
import br.com.bibliotech.dtos.AuthorDTO;
import br.com.bibliotech.entities.Author;
import br.com.bibliotech.repositories.AuthorRepository;
import br.com.bibliotech.responses.AuthorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorResponse save(AuthorDTO authorDTO){
        Author author = new Author(authorDTO);

        authorRepository.save(author);
        log.info("Author Created: " + author);

        return AuthorResponseConverter.convert(author);
    }

    public AuthorResponse getById(Long id){
        Author author = authorRepository.findById(id).orElseThrow();
        return AuthorResponseConverter.convert(author);
    }

    public List<AuthorResponse> getAll(){
        List<Author> authors = authorRepository.findAll();

        return convertEach(authors);
    }

    public AuthorResponse update(AuthorDTO authorDTO, Long id){
        Author author = authorRepository.findById(id).orElseThrow();

        author.setFullName(authorDTO.fullName());
        author.setStageName(authorDTO.stageName());
        author.setBirthdate(authorDTO.birthdate());

        log.info("Author updated: " + author);

        return AuthorResponseConverter.convert(author);
    }

    public void delete(Long id){
        Author author = authorRepository.findById(id).orElseThrow();
        author.setDeleted(true);

        log.info("Author deleted: " + author);
    }

    private List<AuthorResponse> convertEach(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = AuthorResponseConverter.convert(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }

}
