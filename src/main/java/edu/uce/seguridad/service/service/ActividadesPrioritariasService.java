package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ActividadesPrioritarias;
import edu.uce.seguridad.model.PeriodoTolerable;

import java.util.List;

public interface ActividadesPrioritariasService extends BaseService<ActividadesPrioritarias, String> {
    List<ActividadesPrioritarias> buscarActividadesPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
}
