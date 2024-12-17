package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.repository.Genres;
import br.com.bibliotech.infrastructure.exception.ConflictException;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class GenresImpl implements Genres {

    private final GenreRepository genreRepository;

    @Autowired
    GenresImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    @Transactional
    public void save(Genre genre) {
        try {
            genreRepository.save(genre);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Genre named: " + genre.getName() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void update(Genre genre) {
        try {
            genreRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new ConflictException("Genre named: " + genre.getName() + " already exist!");
        }
    }

    @Override
    @Transactional
    public void delete(Genre genre) {
        genre.delete();
        genreRepository.flush();
    }

    @Override
    public Genre findById(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isEmpty())
            throw new NotFoundException("Genre with id: " + id + " not found!");

        return genreOptional.get();
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }


    @Override
    public Genre findByName(String name) {
        Optional<Genre> optionalGenre = genreRepository.findByName(name);
        if (optionalGenre.isEmpty())
            throw new NotFoundException("Genre with name: " + name + " not found!");

        return optionalGenre.get();
    }
}
