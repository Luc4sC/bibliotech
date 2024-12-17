package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.responses.GenreResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreConverter {

    public Genre fromDTO(GenreDTO genreDTO) {
        return new Genre(genreDTO.name());
    }

    public GenreResponse fromModel(Genre genre) {
        return new GenreResponse(genre.getName(), genre.isDeleted());
    }

    public List<GenreResponse> fromModelList(List<Genre> genres) {
        List<GenreResponse> genreResponses = new ArrayList<>();
        genres.forEach(genre -> genreResponses.add(fromModel(genre)));

        return genreResponses;
    }

    public Genre fromDTO(Long id, GenreDTO genreDTO) {
        return new Genre(id, genreDTO.name());
    }
}
