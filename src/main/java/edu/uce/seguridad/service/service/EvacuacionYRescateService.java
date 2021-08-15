package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.EvacuacionYRescate;

import java.util.Collection;

public interface EvacuacionYRescateService extends BaseService<EvacuacionYRescate, String> {

    EvacuacionYRescate buscarPorUsuarioYRiesgo(String usuario, String riesgo) throws NoEncontradoExcepcion;

    Collection<EvacuacionYRescate> buscarPorUsuario(String usuario) throws NoEncontradoExcepcion;

    void iniciarEvacuacionYRescate(String riesgoIngresado, String usuario);
}
