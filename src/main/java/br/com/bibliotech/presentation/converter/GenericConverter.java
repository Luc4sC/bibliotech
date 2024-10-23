package br.com.bibliotech.presentation.converter;


import java.util.List;

interface GenericConverter<M, D, R> {

    M fromDto(D dto);
    R fromModel(M model);
    List<R> fromModelList(List<M> models);

}
