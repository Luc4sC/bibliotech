package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Copy;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.CopyResponse;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class CopyResponseConverter implements Converter<CopyResponse, Copy> {

    @Override
    public CopyResponse convert(Copy copy) {
        BookResponseConverter bookResponseConverter = new BookResponseConverter();
        BookResponse bookResponse = bookResponseConverter.convert(copy.getBook());

        return new CopyResponse(copy.getNumeration(), copy.isAvailable(), copy.getIsbn(), bookResponse);
    }

    @Override
    public List<CopyResponse> convertEach(List<Copy> copies) {
        List<CopyResponse> copyResponses = new ArrayList<>();

        copies.forEach(copy -> {
            CopyResponse copyResponse = convert(copy);
            copyResponses.add(copyResponse);
        });

        return copyResponses;
    }
}
