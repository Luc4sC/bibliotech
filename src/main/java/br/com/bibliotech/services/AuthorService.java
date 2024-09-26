package br.com.bibliotech.services;

import br.com.bibliotech.dtos.AuthorDTO;
import br.com.bibliotech.entities.Author;
import br.com.bibliotech.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(AuthorDTO authorDTO){
        Author author = new Author(authorDTO);
        authorRepository.save(author);
        log.info("Author Created: " + author);
    }

    public AuthorDTO getById(Long id){
        return new AuthorDTO(findById(id));
    }

    public List<AuthorDTO> getAll(){
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOS = new ArrayList<>();

        authors.forEach(author -> {
            AuthorDTO authorDTO = new AuthorDTO(author);
            authorDTOS.add(authorDTO);
        });

        return authorDTOS;
    }

    public void update(AuthorDTO authorDTO, Long id){
        Author author = findById(id);

        author.setFullName(authorDTO.fullName());
        author.setStageName(authorDTO.stageName());
        author.setBirthdate(authorDTO.birthdate());

        log.info("Author updated: " + author);
    }

    public void delete(Long id){
        Author author = findById(id);
        log.info("Author Deleted: " + author);
    }

    private Author findById(Long id){
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            throw new RuntimeException();

        return optionalAuthor.get();
    }

}
