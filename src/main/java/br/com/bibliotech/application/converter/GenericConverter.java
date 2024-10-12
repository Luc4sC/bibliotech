package br.com.bibliotech.application.converter;

import java.util.List;

interface GenericConverter<M, D, R> {

    M modelFromDTO(D dto);
    R responseFromModel(M model);
    List<R> responseListFromModelList(List<M> models);
}
