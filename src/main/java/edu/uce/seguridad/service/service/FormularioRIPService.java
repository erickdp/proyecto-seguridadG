package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.FormularioRIP;

import java.util.List;

public interface FormularioRIPService extends BaseService<FormularioRIP, String> {

    List<FormularioRIP> buscarPorUserFiltradoPorPrioridad(String user) throws NoEncontradoExcepcion;

    List<FormularioRIP> buscarPorUser(String user) throws NoEncontradoExcepcion;

    void eliminarPorUsusario(String user);

    FormularioRIP getMayorPrioridad(String user);
}
