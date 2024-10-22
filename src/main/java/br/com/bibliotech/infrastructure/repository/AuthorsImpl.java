package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.domain.repository.Authors;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
class AuthorsImpl implements Authors {

    private final AuthorRepository authorRepository;

    @Autowired
    AuthorsImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void save(Author model) {
        authorRepository.save(model);
    }

    @Override
    @Transactional
    public void update(Author model, Author update) {
        model.setFullName(update.getFullName());
        model.setStageName(update.getStageName());
        model.setBirthdate(update.getBirthdate());
    }

    @Override
    @Transactional
    public void delete(Author model) {
        model.setDeleted(true);
    }

    @Override
    public Author findById(Author model) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }
}
