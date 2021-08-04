package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ListaEvaluacion;

import java.util.List;

public interface ListaEvaluacionService extends BaseService<ListaEvaluacion, String> {

    List<ListaEvaluacion> buscarPorUserFiltrarPorTipoCalidad(String user) throws NoEncontradoExcepcion;

    void eliminarEvaluacionesPorUser(String user);
}
