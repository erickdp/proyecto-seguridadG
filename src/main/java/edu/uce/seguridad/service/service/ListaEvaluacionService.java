package edu.uce.seguridad.service.service;

import edu.uce.seguridad.model.ListaEvaluacion;

import java.util.List;

public interface ListaEvaluacionService extends BaseService<ListaEvaluacion, String> {

    List<ListaEvaluacion> buscarPorUserFiltrarPorTipoCalidad(String user);

}
