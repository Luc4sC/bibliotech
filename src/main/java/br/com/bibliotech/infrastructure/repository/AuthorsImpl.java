package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class AuthorsImpl implements Authors {

    private final AuthorRepository authorRepository;

    @Autowired
    AuthorsImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void save(Author author) {
        try {
            authorRepository.save(author);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Author with stage name: " + author.getStageName() + " already exists!");
        }
    }

    @Override
    @Transactional
    public void update(Author author) {
        try {
            authorRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Author with stage name: " + author.getStageName() + " already exists!");
        }
    }

    @Override
    @Transactional
    public void delete(Author author) {
        author.delete();
        authorRepository.flush();
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            throw new NotFoundException("Author with id: " + id + " not found!");

        return optionalAuthor.get();
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }


    @Override
    public Author findByStageName(String stageName) {
        Optional<Author> optionalAuthor = authorRepository.findByStageName(stageName);
        if (optionalAuthor.isEmpty())
            throw new NotFoundException("Author with stage name: " + stageName + " not found!");

        return optionalAuthor.get();
    }
}
