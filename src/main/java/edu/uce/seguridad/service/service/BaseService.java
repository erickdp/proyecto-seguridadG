package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.EliminacionException;
import edu.uce.seguridad.exception.MiClaseException;
import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BaseService<T, L> {

//    Para capturas las excepciones deben de agregar el tipo que se debe lanzar y puede ser personalizado
//    En estos metodos no, porque son la base de la mayoria
    List<T> buscarTodos() throws NoEncontradoExcepcion;

    T agregar(T pojo) throws DataAccessException;

//    La insecion y actualizacion la veo un poco redundante pero necesaria
    T actualizar(T pojo) throws DataAccessException;

    T buscaPorId(L identificador) throws NoEncontradoExcepcion;

    void eliminarDocumento(L identificador) throws EliminacionException;
}
