package br.com.bibliotech.presentation.converter;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.dto.AuthorDTO;
import br.com.bibliotech.presentation.responses.AuthorResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter {

    //TODO Matar interface Converter

    public Author fromDto(AuthorDTO authorDTO) {
        return new Author(authorDTO.fullName(), authorDTO.stageName(), authorDTO.birthdate());
    }


    public AuthorResponse fromModel(Author author) {
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate());
    }

    public List<AuthorResponse> fromModelList(List<Author> authors) {
        List<AuthorResponse> authorResponses = new ArrayList<>();
        authors.forEach(model -> {
            AuthorResponse authorResponse = fromModel(model);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }
}
