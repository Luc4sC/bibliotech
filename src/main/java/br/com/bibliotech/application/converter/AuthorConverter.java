package br.com.bibliotech.application.converter;

import br.com.bibliotech.application.dto.AuthorDTO;
import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.response.AuthorResponse;

import java.util.ArrayList;
import java.util.List;

public class AuthorConverter implements GenericConverter<Author, AuthorDTO, AuthorResponse> {

    @Override
    public Author modelFromDTO(AuthorDTO authorDTO){
        return new Author(authorDTO.fullName(), authorDTO.stageName(), authorDTO.birthdate());
    }

    @Override
    public AuthorResponse responseFromModel(Author author){
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate());
    }

    @Override
    public List<AuthorResponse> responseListFromModelList(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = responseFromModel(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }
}
