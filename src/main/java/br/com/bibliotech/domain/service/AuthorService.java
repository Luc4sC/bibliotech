package br.com.bibliotech.domain.service;

import br.com.bibliotech.application.exception.BadRequestException;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthorService {

    private final Authors authors;

    @Autowired
    AuthorService(Authors authors) {
        this.authors = authors;
    }

    public void save(Author author) {
        validName(author, author.getStageName());

        authors.save(author);
        log.info("Author created: " + author);
    }

    public void update(Author author, Author update) {
        validName(author, update.getStageName());

        authors.update(author, update);
        log.info("Author updated: " + author);
    }

    public void delete(Author author) {
        authors.delete(author);
        log.info("Author deleted: " + author);
    }

    public Author findById(Long id) {
        return authors.findById(id);
    }

    public List<Author> findAll() {
        return authors.findAll();
    }

    public Author findByStageName(String stageName) {
        return authors.findByStageName(stageName);
    }

    private void validName(Author author, String stageName) {
        try {
            Author authorsByStageName = authors.findByStageName(stageName);
            if (!author.equals(authorsByStageName))
                throw new BadRequestException("Author with stage name: " + stageName + " already exist!");

        } catch (NotFoundException ignored) {}

    }

}
