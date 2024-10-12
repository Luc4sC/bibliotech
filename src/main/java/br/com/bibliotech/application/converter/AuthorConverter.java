package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.AuthorDTO;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.response.AuthorResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter {

    public Author dtoToModel(AuthorDTO authorDTO){
        return new Author(authorDTO.fullName(), authorDTO.stageName(), authorDTO.birthdate());
    }

    public AuthorResponse modelToResponse(Author author){
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate());
    }

    public List<AuthorResponse> modelListToResponseList(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = modelToResponse(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }
}
