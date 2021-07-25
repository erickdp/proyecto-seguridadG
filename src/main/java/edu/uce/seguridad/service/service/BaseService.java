package edu.uce.seguridad.service.service;

import java.util.List;

public interface BaseService<T, L> {

    /*
    Busca todos los documentos pertenecientes al objeto T
    * */
    List<T> buscarTodos();

    /*
    Agrega los documentos de tipo T y los devuelve
    * */
    T agregar(T pojo);

    /*
    Actualiza los documentos y devuelve el objeto acutalizado
    * */
    T actualizar(T pojo);

    /*
    El identificador L permite obtener el objeto T
    * */
    T buscaPorId(L identificador);

    // En algunos casos el objeto tiene estado 1 = ACTIVO, 2 = INACTIVO o solamente se lo elimina
    void eliminarDocumento(L identificador);
}
