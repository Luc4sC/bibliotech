package br.com.bibliotech.convertes;

import java.util.List;

public interface Converter<T,J> {

    T convert(J object);
    List<T> convertEach(List<J> objectList);
}
