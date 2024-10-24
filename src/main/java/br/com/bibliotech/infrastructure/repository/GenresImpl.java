package br.com.bibliotech.infrastructure.repository;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.domain.repository.Genres;
import br.com.bibliotech.infrastructure.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void save(Genre model) {
        genreRepository.save(model);
    }

    @Override
    public void update(Genre model, Genre update) {
        model.setName(update.getName());
    }

    @Override
    public void delete(Genre model) {
        model.setDeleted(true);
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
