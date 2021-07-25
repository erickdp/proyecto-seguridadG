package edu.uce.seguridad.service.service;

import java.util.List;

public interface BaseService<T, L> {

    List<T> buscarTodos();

    T agregar(T pojo);

    T actualizar(T pojo);

    T buscaPorId(L identificador);

    void eliminarDocumento(L identificador);
}
