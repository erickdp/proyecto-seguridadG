package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.RevisionContinua;

public interface RevisionContinuaService extends BaseService<RevisionContinua, String> {
    RevisionContinua buscarRevisionPorUsuario(String nombreUsuario) throws NoEncontradoExcepcion;
    void eliminarRespuestaFormularioRevisionContinua(String nombreUsuario);
}
