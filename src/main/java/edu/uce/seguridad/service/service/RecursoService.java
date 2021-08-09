package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.Recurso;

import java.util.List;

public interface RecursoService extends BaseService<Recurso, String>{
    List<Recurso> buscarRecursosPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;

    Recurso buscarRecursoPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
}
