package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.exception.ConflictException;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorDomainService {

    @Autowired
    private Authors authors;

    public void save(Author author){
        if (isStageNameUnavailable(author.getStageName(), author))
            throw new ConflictException("Author with stage name: " + author.getStageName() + " already exist");
        
        authors.save(author);
        log.info("Author Created: " + author);
    }

    public void update(Author author, Author authorUpdate){
        if(isStageNameUnavailable(authorUpdate.getStageName(), author))
            throw new ConflictException("Author with stage name: " + authorUpdate.getStageName() + " already exist!");

        authors.update(author, authorUpdate);
        log.info("Author updated: " + author);
    }

    public void delete(Long id){
        Author author = authors.findById(id);
        authors.delete(author);

        log.info("Author deleted: " + author);
    }

    public Author findById(Long id){
        return authors.findById(id);
    }

    public List<Author> findAll(){
        return authors.findAll();
    }

    public Author findByStageName(String stageName){
        return authors.findByStageName(stageName);
    }

    private boolean isStageNameUnavailable(String stageName, Author author){
        try {
            Author byStageName = authors.findByStageName(stageName);
            return !author.equals(byStageName);
        } catch (NotFoundException e){
            return false;
        }
    }

}
