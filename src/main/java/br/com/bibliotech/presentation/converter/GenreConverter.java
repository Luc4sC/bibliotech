package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.presentation.dto.GenreDTO;
import br.com.bibliotech.presentation.responses.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenreConverter implements GenericConverter<Genre, GenreDTO, GenreResponse> {
    @Override
    public Genre fromDto(GenreDTO dto) {
        return new Genre(dto.name());
    }

    @Override
    public GenreResponse fromModel(Genre model) {
        return new GenreResponse(model.getName());
    }

    @Override
    public List<GenreResponse> fromModelList(List<Genre> models) {
        List<GenreResponse> genreResponses = new ArrayList<>();
        models.forEach(model -> {
            GenreResponse genreResponse = fromModel(model);
            genreResponses.add(genreResponse);
        });

        return genreResponses;
    }
}
