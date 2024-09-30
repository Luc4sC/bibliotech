package br.com.bibliotech.convertes;

import br.com.bibliotech.entities.Copy;
import br.com.bibliotech.responses.CopyResponse;

import java.util.List;

public class CopyResponseConverter implements Converter<CopyResponse, Copy> {
    @Override
    public CopyResponse convert(Copy copy) {
        return null;
    }

    @Override
    public List<CopyResponse> convertEach(List<Copy> copies) {
        return null;
    }
}
