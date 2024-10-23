package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.dto.AuthorDTO;
import br.com.bibliotech.presentation.responses.AuthorResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter implements GenericConverter<Author, AuthorDTO, AuthorResponse> {
    @Override
    public Author fromDto(AuthorDTO dto) {
        return new Author(dto.fullName(), dto.stageName(), dto.birthdate());
    }

    @Override
    public AuthorResponse fromModel(Author model) {
        return new AuthorResponse(model.getFullName(), model.getStageName(), model.getBirthdate());
    }

    @Override
    public List<AuthorResponse> fromModelList(List<Author> models) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        models.forEach(model -> {
            AuthorResponse authorResponse = fromModel(model);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }
}
