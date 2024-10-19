package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.repository.Genres;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class GenresImpl implements Genres {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    @Transactional
    public void save(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(Genre genre) {
        genre.setDeleted(true);
    }

    @Override
    @Transactional
    public void update(Genre genre, Genre genreUpdate) {
        genre.setName(genreUpdate.getName());
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
        Optional<Genre> genreOptional = genreRepository.findByName(name);
        if (genreOptional.isEmpty())
            throw new NotFoundException("Genre with name: " + name + " not found!");

        return genreOptional.get();
    }
}
