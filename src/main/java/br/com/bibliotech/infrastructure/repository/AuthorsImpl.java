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
    public Author findById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())
            throw new NotFoundException("Author with id: " + id + " not found!");

        return optionalAuthor.get();
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    @Override
    public Author findByStageName(String stageName) {
        Optional<Author> optionalAuthor = authorRepository.findByStageName(stageName);
        if (optionalAuthor.isEmpty())
            throw new NotFoundException("Author with stage name: " + stageName + " not found!");

        return optionalAuthor.get();
    }
}
