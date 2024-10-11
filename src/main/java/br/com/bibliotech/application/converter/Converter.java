package br.com.bibliotech.application.converter;

import java.util.List;

public interface Converter<T,J> {

    T convert(J object);
    List<T> convertEach(List<J> objectList);
}
