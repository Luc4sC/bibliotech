package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class AuthorsImpl implements Authors {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author findByStageName(String stageName) {
        Optional<Author> authorOptional = authorRepository.findByStageName(stageName);
        if(authorOptional.isEmpty())
            throw new NotFoundException("Author with stage name: " + stageName + " not found!");

        return authorOptional.get();
    }

    @Override
    @Transactional
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    @Transactional
    public void update(Author author, Author authorUpdate) {
        author.setFullName(authorUpdate.getFullName());
        author.setStageName(authorUpdate.getStageName());
        author.setBirthdate(author.getBirthdate());
    }

    @Override
    @Transactional
    public void delete(Author author) {
        author.setDeleted(true);
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(authorOptional.isEmpty())
            throw new NotFoundException("Author with id: " + id + " not found!");

        return authorOptional.get();
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
