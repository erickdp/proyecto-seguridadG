package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Recurso;

public interface RecursoService extends BaseService<Recurso, String>{
    Recurso buscarRecursoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
}
