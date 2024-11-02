package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.responses.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenreConverter {

    public Genre fromDto(GenreDTO genreDTO) {
        return new Genre(genreDTO.name());
    }

    public GenreResponse fromModel(Genre genre) {
        return new GenreResponse(genre.getName(), genre.isDeleted());
    }

    public List<GenreResponse> fromModelList(List<Genre> genres) {
        List<GenreResponse> genreResponses = new ArrayList<>();
        genres.forEach(model -> {
            GenreResponse genreResponse = fromModel(model);
            genreResponses.add(genreResponse);
        });

        return genreResponses;
    }

}
