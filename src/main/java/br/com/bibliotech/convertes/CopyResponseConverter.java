package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Copy;
import br.com.bibliotech.responses.BookResponse;
import br.com.bibliotech.responses.CopyResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CopyResponseConverter implements Converter<CopyResponse, Copy> {

    @Autowired
    private BookResponseConverter bookResponseConverter;

    @Override
    public CopyResponse convert(Copy copy) {
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
