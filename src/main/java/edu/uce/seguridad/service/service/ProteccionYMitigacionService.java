package edu.uce.seguridad.service.service;

import edu.uce.seguridad.exception.NoEncontradoExcepcion;
import edu.uce.seguridad.model.ProteccionYMitigacion;

import java.util.Collection;

public interface ProteccionYMitigacionService extends BaseService<ProteccionYMitigacion, String> {
    Collection<ProteccionYMitigacion> obtenerPorUsuario(String usuario) throws NoEncontradoExcepcion;
    ProteccionYMitigacion obtenerPorFormPorRiesgoYUsuario(String riesgo, String usuario) throws NoEncontradoExcepcion;
}
