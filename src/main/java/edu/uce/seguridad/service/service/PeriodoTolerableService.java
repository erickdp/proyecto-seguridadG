package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.PeriodoTolerable;

import java.util.List;

public interface PeriodoTolerableService extends BaseService<PeriodoTolerable, String> {
    List<PeriodoTolerable> buscarPeriodosPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
}
