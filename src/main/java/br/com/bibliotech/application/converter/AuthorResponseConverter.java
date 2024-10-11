package br.com.bibliotech.application.converter;

import br.com.bibliotech.domain.model.Author;
import br.com.bibliotech.presentation.response.AuthorResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class AuthorResponseConverter implements Converter<AuthorResponse, Author>{

    public AuthorResponse convert(Author author){
        return new AuthorResponse(author.getFullName(), author.getStageName(), author.getBirthdate());
    }

    public List<AuthorResponse> convertEach(List<Author> authors){
        List<AuthorResponse> authorResponses = new ArrayList<>();

        authors.forEach(author -> {
            AuthorResponse authorResponse = convert(author);
            authorResponses.add(authorResponse);
        });

        return authorResponses;
    }

}
