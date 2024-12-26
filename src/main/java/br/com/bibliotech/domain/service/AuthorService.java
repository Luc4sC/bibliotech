package br.com.bibliotech.domain.service;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorService {

    private final Authors authors;

    @Autowired
    AuthorService(Authors authors) {
        this.authors = authors;
    }

    public void save(Author author) {
        authors.save(author);
        log.info("Author created: " + author);
    }

    public void update(Author author) {
        authors.update(author);
        log.info("Author updated: " + author);
    }

    public void delete(Long id) {
        Author author = findById(id);
        authors.delete(author);

        log.info("Author deleted: " + author);
    }

    public Author findById(Long id) {
        return authors.findById(id);
    }

    public Page<Author> findAll(Pageable pageable) {
        return authors.findAll(pageable);
    }

    public Author findByStageName(String stageName) {
        return authors.findByStageName(stageName);
    }

}
