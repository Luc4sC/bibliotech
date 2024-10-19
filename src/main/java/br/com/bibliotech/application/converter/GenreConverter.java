package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.GenreDTO;
import br.com.bibliotech.domain.model.Genre;
import br.com.bibliotech.presentation.response.GenreResponse;

import java.util.ArrayList;
import java.util.List;

public class GenreConverter implements GenericConverter<Genre, GenreDTO, GenreResponse>{

    @Override
    public Genre modelFromDTO(GenreDTO genreDTO) {
        return new Genre(genreDTO.name());
    }

    @Override
    public GenreResponse responseFromModel(Genre genre) {
        return new GenreResponse(genre.getName());
    }

    @Override
    public List<GenreResponse> responseListFromModelList(List<Genre> genres) {
        List<GenreResponse> genreResponses = new ArrayList<>();

        genres.forEach(genre -> {
            GenreResponse genreResponse = responseFromModel(genre);
            genreResponses.add(genreResponse);
        });

        return genreResponses;
    }
}
